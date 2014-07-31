/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights
 * reserved. DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER. This
 * code is free software;Designed and Developed mainly by many Chinese
 * opensource volunteers. you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation. This code is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License version 2 for more details (a copy is included in the LICENSE
 * file that accompanied this code). You should have received a copy of the GNU
 * General Public License version 2 along with this work; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA. Any questions about this component can be directed to it's
 * project Web address https://code.google.com/p/opencloudb/.
 */
package org.opencloudb.parser;

import java.sql.SQLSyntaxErrorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.QueryTreeNode;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.SQLParserFeature;

/**
 * @author mycat
 */
public final class SQLParserDelegate {
    /**
     * 
     */

    public static final String DEFAULT_CHARSET = "utf-8";

    private static final ThreadLocal<SQLParser> sqlParser = new ThreadLocal<SQLParser>() {
        protected SQLParser initialValue() {
            SQLParser parser = new SQLParser();
            parser.getFeatures().add(SQLParserFeature.DOUBLE_QUOTED_STRING);
            parser.getFeatures().add(SQLParserFeature.MYSQL_HINTS);
            parser.getFeatures().add(SQLParserFeature.MYSQL_INTERVAL);
            // fix 位操作符号解析问题 add by micmiu
            parser.getFeatures().add(SQLParserFeature.INFIX_BIT_OPERATORS);
            return parser;
        }

    };

    public static QueryTreeNode parse(String stmt, String string) throws SQLSyntaxErrorException {
        try {
            stmt = processEscape(stmt);
            return sqlParser.get().parseStatement(stmt);
        } catch (StandardException e) {
            throw new SQLSyntaxErrorException(e);
        }
    }

    private static final Pattern p = Pattern.compile("\\'", Pattern.LITERAL);

    private static final String TARGET_STRING = "''";

    private static final char ESCAPE_CHAR = '\\';

    private static final int TARGET_STRING_LENGTH = 2;

    /**
     * mysql driver对'转义与\',解析前改为foundationdb parser支持的'' add by sky
     * 
     * @param stmt
     * @return
     */
    public static String processEscape(String sql) {
        int firstIndex = -1;
        if ((sql == null) || ((firstIndex = sql.indexOf(ESCAPE_CHAR)) == -1)) {
            return sql;
        } else {
            int lastIndex = sql.lastIndexOf(ESCAPE_CHAR, sql.length() - 2);// 不用考虑结尾字符为转义符
            Matcher matcher = p.matcher(sql.substring(firstIndex, lastIndex + TARGET_STRING_LENGTH));
            String replacedStr = (lastIndex == firstIndex) ? matcher.replaceFirst(TARGET_STRING) : matcher
                    .replaceAll(TARGET_STRING);
            StringBuilder sb = new StringBuilder(sql);
            sb.replace(firstIndex, lastIndex + TARGET_STRING_LENGTH, replacedStr);
            return sb.toString();
        }
    }

}