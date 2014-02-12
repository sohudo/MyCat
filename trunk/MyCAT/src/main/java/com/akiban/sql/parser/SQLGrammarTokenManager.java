/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */
package com.akiban.sql.parser;
import com.akiban.sql.StandardException;
import com.akiban.sql.types.AliasInfo;
import com.akiban.sql.types.RoutineAliasInfo;
import com.akiban.sql.types.CharacterTypeAttributes;
import com.akiban.sql.types.DataTypeDescriptor;
import com.akiban.sql.types.TypeId;
import java.sql.ParameterMetaData;
import java.sql.Types;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/** Token Manager. */
class SQLGrammarTokenManager implements SQLGrammarConstants
{
    void CommonTokenAction(Token t) {
        t.beginOffset = input_stream.getBeginOffset();
        t.endOffset = input_stream.getEndOffset();
    }

    int commentNestingDepth = 0;

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }

  /** The parser. */
  public SQLGrammar parser = null;
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      case 47:
         return jjMoveStringLiteralDfa1_1(0x40L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}
private final int jjStopStringLiteralDfa_17(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_17(int pos, long active0)
{
   return jjMoveNfa_17(jjStopStringLiteralDfa_17(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_17()
{
   switch(curChar)
   {
      case 69:
         return jjStopAtPos(0, 56);
      case 101:
         return jjStopAtPos(0, 56);
      default :
         return jjMoveNfa_17(0, 0);
   }
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_17(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 58)
                     kind = 58;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 57)
                        kind = 57;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 57)
                     kind = 57;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 58)
                     kind = 58;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffdfffffffdfL & l) != 0L)
                     kind = 58;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 58)
                     kind = 58;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_16(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_16(int pos, long active0)
{
   return jjMoveNfa_16(jjStopStringLiteralDfa_16(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_16()
{
   switch(curChar)
   {
      case 73:
         return jjStopAtPos(0, 53);
      case 105:
         return jjStopAtPos(0, 53);
      default :
         return jjMoveNfa_16(0, 0);
   }
}
private int jjMoveNfa_16(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 55)
                     kind = 55;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 54)
                        kind = 54;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 54)
                     kind = 54;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 55)
                     kind = 55;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffdfffffffdffL & l) != 0L)
                     kind = 55;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 55)
                     kind = 55;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_19()
{
   return jjMoveNfa_19(4, 0);
}
private int jjMoveNfa_19(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((0xffffffffffffdbffL & l) != 0L)
                  {
                     if (kind > 63)
                        kind = 63;
                     jjCheckNAddStates(0, 2);
                  }
                  else if ((0x2400L & l) != 0L)
                  {
                     if (kind > 63)
                        kind = 63;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  kind = 63;
                  jjCheckNAddStates(0, 2);
                  break;
               case 1:
                  if ((0x2400L & l) != 0L && kind > 63)
                     kind = 63;
                  break;
               case 2:
                  if (curChar == 10 && kind > 63)
                     kind = 63;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  kind = 63;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_12(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_12(int pos, long active0)
{
   return jjMoveNfa_12(jjStopStringLiteralDfa_12(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_12()
{
   switch(curChar)
   {
      case 80:
         return jjStopAtPos(0, 41);
      case 112:
         return jjStopAtPos(0, 41);
      default :
         return jjMoveNfa_12(0, 0);
   }
}
private int jjMoveNfa_12(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 43)
                     kind = 43;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 42)
                        kind = 42;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 42)
                     kind = 42;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 43)
                     kind = 43;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffefffffffeffffL & l) != 0L)
                     kind = 43;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 43)
                     kind = 43;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_22()
{
   return jjMoveNfa_22(6, 0);
}
static final long[] jjbitVec3 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0x7fffffffffffffffL
};
static final long[] jjbitVec4 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0x1fffffffL
};
private int jjMoveNfa_22(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 6;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  else if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 0:
                  if (curChar == 34)
                     jjCheckNAddStates(3, 6);
                  break;
               case 1:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 3:
                  if ((0xfffffffffffffffeL & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  break;
               case 4:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  break;
               case 5:
                  if (curChar == 34 && kind > 550)
                     kind = 550;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  else if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 2:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 3:
                  jjCheckNAddStates(3, 6);
                  break;
               case 4:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(3, 6);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
               case 4:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(3, 6);
                  break;
               case 3:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(3, 6);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 6 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_6(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_6(int pos, long active0)
{
   return jjMoveNfa_6(jjStopStringLiteralDfa_6(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_6()
{
   switch(curChar)
   {
      case 66:
         return jjStopAtPos(0, 23);
      case 98:
         return jjStopAtPos(0, 23);
      default :
         return jjMoveNfa_6(0, 0);
   }
}
private int jjMoveNfa_6(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 25)
                     kind = 25;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 24)
                     kind = 24;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 25)
                     kind = 25;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffffbfffffffbL & l) != 0L)
                     kind = 25;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 25)
                     kind = 25;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_2(0x200L);
      case 47:
         return jjMoveStringLiteralDfa1_2(0x100L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_2(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x100L) != 0L)
            return jjStopAtPos(1, 8);
         break;
      case 47:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(1, 9);
         break;
      default :
         return 2;
   }
   return 2;
}
private final int jjStopStringLiteralDfa_15(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_15(int pos, long active0)
{
   return jjMoveNfa_15(jjStopStringLiteralDfa_15(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_15()
{
   switch(curChar)
   {
      case 84:
         return jjStopAtPos(0, 50);
      case 116:
         return jjStopAtPos(0, 50);
      default :
         return jjMoveNfa_15(0, 0);
   }
}
private int jjMoveNfa_15(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 52)
                     kind = 52;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 51)
                        kind = 51;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 51)
                     kind = 51;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 52)
                     kind = 52;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffefffffffefffffL & l) != 0L)
                     kind = 52;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 52)
                     kind = 52;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_13(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_13(int pos, long active0)
{
   return jjMoveNfa_13(jjStopStringLiteralDfa_13(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_13()
{
   switch(curChar)
   {
      case 69:
         return jjStopAtPos(0, 44);
      case 101:
         return jjStopAtPos(0, 44);
      default :
         return jjMoveNfa_13(0, 0);
   }
}
private int jjMoveNfa_13(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 46)
                     kind = 46;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 45)
                        kind = 45;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 45)
                     kind = 45;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 46)
                     kind = 46;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffdfffffffdfL & l) != 0L)
                     kind = 46;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 46)
                     kind = 46;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_7(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_7(int pos, long active0)
{
   return jjMoveNfa_7(jjStopStringLiteralDfa_7(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_7()
{
   switch(curChar)
   {
      case 89:
         return jjStopAtPos(0, 26);
      case 121:
         return jjStopAtPos(0, 26);
      default :
         return jjMoveNfa_7(0, 0);
   }
}
private int jjMoveNfa_7(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 28)
                     kind = 28;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 27)
                     kind = 27;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 28)
                     kind = 28;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfdfffffffdffffffL & l) != 0L)
                     kind = 28;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 28)
                     kind = 28;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_11(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_11(int pos, long active0)
{
   return jjMoveNfa_11(jjStopStringLiteralDfa_11(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_11()
{
   switch(curChar)
   {
      case 79:
         return jjStopAtPos(0, 38);
      case 111:
         return jjStopAtPos(0, 38);
      default :
         return jjMoveNfa_11(0, 0);
   }
}
private int jjMoveNfa_11(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 40)
                     kind = 40;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 39)
                     kind = 39;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 40)
                     kind = 40;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffff7fffffff7fffL & l) != 0L)
                     kind = 40;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 40)
                     kind = 40;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_3()
{
   switch(curChar)
   {
      case 9:
         {
         jjmatchedKind = 13;
         jjmatchedPos = 0;
         }
         return jjMoveNfa_3(0, 0);
      case 32:
         {
         jjmatchedKind = 12;
         jjmatchedPos = 0;
         }
         return jjMoveNfa_3(0, 0);
      case 68:
         {
         jjmatchedKind = 14;
         jjmatchedPos = 0;
         }
         return jjMoveNfa_3(0, 0);
      case 100:
         {
         jjmatchedKind = 14;
         jjmatchedPos = 0;
         }
         return jjMoveNfa_3(0, 0);
      default :
         return jjMoveNfa_3(0, 0);
   }
}
private int jjMoveNfa_3(int startState, int curPos)
{
   int strKind = jjmatchedKind;
   int strPos = jjmatchedPos;
   int seenUpto;
   input_stream.backup(seenUpto = curPos + 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { throw new Error("Internal Error"); }
   curPos = 0;
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 16)
                     kind = 16;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 15)
                        kind = 15;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 15)
                     kind = 15;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 16)
                     kind = 16;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffefffffffefL & l) != 0L)
                     kind = 16;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 16)
                     kind = 16;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         break;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { break; }
   }
   if (jjmatchedPos > strPos)
      return curPos;

   int toRet = Math.max(curPos, seenUpto);

   if (curPos < toRet)
      for (i = toRet - Math.min(curPos, seenUpto); i-- > 0; )
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) { throw new Error("Internal Error : Please send a bug report."); }

   if (jjmatchedPos < strPos)
   {
      jjmatchedKind = strKind;
      jjmatchedPos = strPos;
   }
   else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
      jjmatchedKind = strKind;

   return toRet;
}
private int jjMoveStringLiteralDfa0_23()
{
   switch(curChar)
   {
      case 36:
         return jjMoveStringLiteralDfa1_23(0x10000000000L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_23(long active8)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 36:
         if ((active8 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 552);
         break;
      default :
         return 2;
   }
   return 2;
}
private int jjMoveStringLiteralDfa0_20()
{
   return jjMoveNfa_20(4, 0);
}
private int jjMoveNfa_20(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((0xffffffffffffdbffL & l) != 0L)
                  {
                     if (kind > 64)
                        kind = 64;
                     jjCheckNAddStates(0, 2);
                  }
                  else if ((0x2400L & l) != 0L)
                  {
                     if (kind > 64)
                        kind = 64;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  kind = 64;
                  jjCheckNAddStates(0, 2);
                  break;
               case 1:
                  if ((0x2400L & l) != 0L && kind > 64)
                     kind = 64;
                  break;
               case 2:
                  if (curChar == 10 && kind > 64)
                     kind = 64;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  kind = 64;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_5(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_5(int pos, long active0)
{
   return jjMoveNfa_5(jjStopStringLiteralDfa_5(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_5()
{
   switch(curChar)
   {
      case 82:
         return jjStopAtPos(0, 20);
      case 114:
         return jjStopAtPos(0, 20);
      default :
         return jjMoveNfa_5(0, 0);
   }
}
private int jjMoveNfa_5(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 22)
                     kind = 22;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 21)
                        kind = 21;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 21)
                     kind = 21;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 22)
                     kind = 22;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffbfffffffbffffL & l) != 0L)
                     kind = 22;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 22)
                     kind = 22;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_4(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_4(int pos, long active0)
{
   return jjMoveNfa_4(jjStopStringLiteralDfa_4(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_4()
{
   switch(curChar)
   {
      case 69:
         return jjStopAtPos(0, 17);
      case 101:
         return jjStopAtPos(0, 17);
      default :
         return jjMoveNfa_4(0, 0);
   }
}
private int jjMoveNfa_4(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 19)
                     kind = 19;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 18)
                        kind = 18;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 18)
                     kind = 18;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 19)
                     kind = 19;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffdfffffffdfL & l) != 0L)
                     kind = 19;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 19)
                     kind = 19;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_8(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_8(int pos, long active0)
{
   return jjMoveNfa_8(jjStopStringLiteralDfa_8(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_8()
{
   switch(curChar)
   {
      case 45:
         return jjStopAtPos(0, 29);
      default :
         return jjMoveNfa_8(0, 0);
   }
}
private int jjMoveNfa_8(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffdfffffffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                  }
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 30)
                     kind = 30;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if ((0xffffdfffffffffffL & l) != 0L && kind > 31)
                     kind = 31;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  kind = 31;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 31)
                     kind = 31;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_10(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_10(int pos, long active0)
{
   return jjMoveNfa_10(jjStopStringLiteralDfa_10(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_10()
{
   switch(curChar)
   {
      case 82:
         return jjStopAtPos(0, 35);
      case 114:
         return jjStopAtPos(0, 35);
      default :
         return jjMoveNfa_10(0, 0);
   }
}
private int jjMoveNfa_10(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 37)
                     kind = 37;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 36)
                        kind = 36;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 36)
                     kind = 36;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 37)
                     kind = 37;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffbfffffffbffffL & l) != 0L)
                     kind = 37;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 37)
                     kind = 37;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 9:
         jjmatchedKind = 2;
         return jjMoveNfa_0(0, 0);
      case 10:
         jjmatchedKind = 3;
         return jjMoveNfa_0(0, 0);
      case 13:
         jjmatchedKind = 4;
         return jjMoveNfa_0(0, 0);
      case 32:
         jjmatchedKind = 1;
         return jjMoveNfa_0(0, 0);
      case 33:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x8000L);
      case 34:
         jjmatchedKind = 548;
         return jjMoveNfa_0(0, 0);
      case 36:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x8000000000L);
      case 37:
         jjmatchedKind = 504;
         return jjMoveNfa_0(0, 0);
      case 38:
         jjmatchedKind = 505;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x8000000L);
      case 39:
         jjmatchedKind = 509;
         return jjMoveNfa_0(0, 0);
      case 40:
         jjmatchedKind = 512;
         return jjMoveNfa_0(0, 0);
      case 41:
         jjmatchedKind = 513;
         return jjMoveNfa_0(0, 0);
      case 42:
         jjmatchedKind = 514;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x2000000L);
      case 43:
         jjmatchedKind = 515;
         return jjMoveNfa_0(0, 0);
      case 44:
         jjmatchedKind = 516;
         return jjMoveNfa_0(0, 0);
      case 45:
         jjmatchedKind = 517;
         return jjMoveStringLiteralDfa1_0(0x800L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x1000000L);
      case 46:
         jjmatchedKind = 518;
         return jjMoveNfa_0(0, 0);
      case 47:
         jjmatchedKind = 519;
         return jjMoveStringLiteralDfa1_0(0x20L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L);
      case 58:
         jjmatchedKind = 520;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x200L);
      case 59:
         jjmatchedKind = 522;
         return jjMoveNfa_0(0, 0);
      case 60:
         jjmatchedKind = 523;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x405000L);
      case 61:
         jjmatchedKind = 525;
         return jjMoveNfa_0(0, 0);
      case 62:
         jjmatchedKind = 528;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x820000L);
      case 63:
         jjmatchedKind = 530;
         return jjMoveNfa_0(0, 0);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x3ffeL, 0x0L, 0x0L, 0x3c00000L, 0x0L, 0x1000L, 0x0L, 0x0L);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x0L, 0xfc000L, 0x0L, 0x0L, 0x4000000L, 0x2000000000000L, 0x4000000000e000L, 0x0L, 0x0L);
      case 67:
         jjmatchedKind = 283;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x7fffffff00000L, 0x0L, 0x0L, 0xff0000000L, 0x7c000000000000L, 0x7f0000L, 0x0L, 0x0L);
      case 68:
         jjmatchedKind = 115;
         return jjMoveStringLiteralDfa1_0(0x0L, 0xfff0000000000000L, 0x7L, 0x0L, 0x1ff000000000L, 0x80000000000000L, 0xff800000L, 0x0L, 0x0L);
      case 69:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1ff8L, 0x0L, 0x200000000000L, 0x400000000000000L, 0xf00000000L, 0x0L, 0x0L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x7fe000L, 0x0L, 0x400000000000L, 0x0L, 0xf000000000L, 0x0L, 0x0L);
      case 71:
         jjmatchedKind = 544;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x3f800000L, 0x0L, 0x800000000000L, 0x300000000000000L, 0x0L, 0x0L, 0x0L);
      case 72:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xc0000000L, 0x0L, 0x0L, 0x0L, 0xf0000000000L, 0x0L, 0x0L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1ffff00000000L, 0x0L, 0x3f000000000000L, 0x0L, 0xf00000000000L, 0x0L, 0x0L);
      case 74:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x2000000000000L, 0x0L, 0x0L, 0x0L, 0x1000000000000L, 0x0L, 0x0L);
      case 75:
         jjmatchedKind = 542;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x4000000000000L, 0x0L, 0x0L, 0x0L, 0x2000000000000L, 0x0L, 0x0L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xf8000000000000L, 0x0L, 0x1fc0000000000000L, 0x3800000000000000L, 0xbc000000000000L, 0x0L, 0x0L);
      case 77:
         jjmatchedKind = 543;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1f00000000000000L, 0x0L, 0xe000000000000000L, 0x1fL, 0xff00000000000000L, 0x3L, 0x0L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xe000000000000000L, 0x3fL, 0x0L, 0x40000000000003e0L, 0x0L, 0x1cL, 0x0L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x1ffc0L, 0x0L, 0x8000000000000c00L, 0x0L, 0x3e0L, 0x0L);
      case 80:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x7fe0000L, 0x0L, 0xf000L, 0x0L, 0x3c00L, 0x0L);
      case 81:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0xc000L, 0x0L);
      case 82:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0xff8000000L, 0x0L, 0x3f0000L, 0xfL, 0x3ff0000L, 0x0L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x1ffff000000000L, 0x0L, 0x7fffc00000L, 0x30L, 0x3fffc000000L, 0x0L);
      case 84:
         jjmatchedKind = 245;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0xffc0000000000000L, 0x3L, 0x3f8000000000L, 0x0L, 0xfc0000000000L, 0x0L);
      case 85:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x1fcL, 0xc00000000000L, 0x0L, 0xf000000000000L, 0x0L);
      case 86:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x7e00L, 0x0L, 0x0L, 0x10000000000000L, 0x0L);
      case 87:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x1f8000L, 0x1000000000000L, 0x0L, 0x60000000000000L, 0x0L);
      case 88:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x7c0L, 0x0L, 0x0L);
      case 89:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x200000L, 0x0L, 0x0L, 0x80000000000000L, 0x0L);
      case 90:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x800L, 0x0L, 0x0L);
      case 91:
         jjmatchedKind = 532;
         return jjMoveNfa_0(0, 0);
      case 93:
         jjmatchedKind = 533;
         return jjMoveNfa_0(0, 0);
      case 94:
         jjmatchedKind = 506;
         return jjMoveNfa_0(0, 0);
      case 95:
         jjmatchedKind = 531;
         return jjMoveNfa_0(0, 0);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x3ffeL, 0x0L, 0x0L, 0x3c00000L, 0x0L, 0x1000L, 0x0L, 0x0L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x0L, 0xfc000L, 0x0L, 0x0L, 0x4000000L, 0x2000000000000L, 0x4000000000e000L, 0x0L, 0x0L);
      case 99:
         jjmatchedKind = 283;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x7fffffff00000L, 0x0L, 0x0L, 0xff0000000L, 0x7c000000000000L, 0x7f0000L, 0x0L, 0x0L);
      case 100:
         jjmatchedKind = 115;
         return jjMoveStringLiteralDfa1_0(0x0L, 0xfff0000000000000L, 0x7L, 0x0L, 0x1ff000000000L, 0x80000000000000L, 0xff800000L, 0x0L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1ff8L, 0x0L, 0x200000000000L, 0x400000000000000L, 0xf00000000L, 0x0L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x7fe000L, 0x0L, 0x400000000000L, 0x0L, 0xf000000000L, 0x0L, 0x0L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x3f800000L, 0x0L, 0x800000000000L, 0x300000000000000L, 0x0L, 0x0L, 0x0L);
      case 104:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xc0000000L, 0x0L, 0x0L, 0x0L, 0xf0000000000L, 0x0L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1ffff00000000L, 0x0L, 0x3f000000000000L, 0x0L, 0xf00000000000L, 0x0L, 0x0L);
      case 106:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x2000000000000L, 0x0L, 0x0L, 0x0L, 0x1000000000000L, 0x0L, 0x0L);
      case 107:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x4000000000000L, 0x0L, 0x0L, 0x0L, 0x2000000000000L, 0x0L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xf8000000000000L, 0x0L, 0x1fc0000000000000L, 0x3800000000000000L, 0xbc000000000000L, 0x0L, 0x0L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x1f00000000000000L, 0x0L, 0xe000000000000000L, 0x1fL, 0xff00000000000000L, 0x3L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0xe000000000000000L, 0x3fL, 0x0L, 0x40000000000003e0L, 0x0L, 0x1cL, 0x0L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x1ffc0L, 0x0L, 0x8000000000000c00L, 0x0L, 0x3e0L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x7fe0000L, 0x0L, 0xf000L, 0x0L, 0x3c00L, 0x0L);
      case 113:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0xc000L, 0x0L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0xff8000000L, 0x0L, 0x3f0000L, 0xfL, 0x3ff0000L, 0x0L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x1ffff000000000L, 0x0L, 0x7fffc00000L, 0x30L, 0x3fffc000000L, 0x0L);
      case 116:
         jjmatchedKind = 245;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0xffc0000000000000L, 0x3L, 0x3f8000000000L, 0x0L, 0xfc0000000000L, 0x0L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x1fcL, 0xc00000000000L, 0x0L, 0xf000000000000L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x7e00L, 0x0L, 0x0L, 0x10000000000000L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x1f8000L, 0x1000000000000L, 0x0L, 0x60000000000000L, 0x0L);
      case 120:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x7c0L, 0x0L, 0x0L);
      case 121:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x200000L, 0x0L, 0x0L, 0x80000000000000L, 0x0L);
      case 122:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x800L, 0x0L, 0x0L);
      case 123:
         jjmatchedKind = 510;
         return jjMoveNfa_0(0, 0);
      case 124:
         jjmatchedKind = 507;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x0L, 0x4000000L);
      case 125:
         jjmatchedKind = 511;
         return jjMoveNfa_0(0, 0);
      case 126:
         jjmatchedKind = 508;
         return jjMoveNfa_0(0, 0);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1, long active2, long active3, long active4, long active5, long active6, long active7, long active8)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 0);
   }
   switch(curChar)
   {
      case 36:
         if ((active8 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 551;
            jjmatchedPos = 1;
         }
         break;
      case 38:
         if ((active8 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 539;
            jjmatchedPos = 1;
         }
         break;
      case 42:
         if ((active0 & 0x20L) != 0L)
         {
            jjmatchedKind = 5;
            jjmatchedPos = 1;
         }
         else if ((active8 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 537;
            jjmatchedPos = 1;
         }
         break;
      case 45:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 1;
         }
         break;
      case 58:
         if ((active8 & 0x200L) != 0L)
         {
            jjmatchedKind = 521;
            jjmatchedPos = 1;
         }
         break;
      case 60:
         if ((active8 & 0x400000L) != 0L)
         {
            jjmatchedKind = 534;
            jjmatchedPos = 1;
         }
         break;
      case 61:
         if ((active8 & 0x1000L) != 0L)
         {
            jjmatchedKind = 524;
            jjmatchedPos = 1;
         }
         else if ((active8 & 0x8000L) != 0L)
         {
            jjmatchedKind = 527;
            jjmatchedPos = 1;
         }
         else if ((active8 & 0x20000L) != 0L)
         {
            jjmatchedKind = 529;
            jjmatchedPos = 1;
         }
         break;
      case 62:
         if ((active8 & 0x4000L) != 0L)
         {
            jjmatchedKind = 526;
            jjmatchedPos = 1;
         }
         else if ((active8 & 0x800000L) != 0L)
         {
            jjmatchedKind = 535;
            jjmatchedPos = 1;
         }
         else if ((active8 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 536;
            jjmatchedPos = 1;
         }
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0xf00000L, active2, 0x6308000040002000L, active3, 0x400000000e0000L, active4, 0x20c000f010003e00L, active5, 0x84000000401020L, active6, 0x1000107800000L, active7, 0xc00L, active8, 0L);
      case 66:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0xc00000L, active5, 0x400L, active6, 0x8000000L, active7, 0L, active8, 0L);
      case 67:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x8000000000000000L, active3, 0x3000000040L, active4, 0x1000000L, active5, 0x800040L, active6, 0x4000000000000L, active7, 0x1000000000000L, active8, 0L);
      case 68:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2L, active2, 0x100000000L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L, active8, 0L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x3ff000000000c000L, active2, 0x34000000804000L, active3, 0x8007c1f8000001L, active4, 0x300830000200000L, active5, 0x1000000070f0000L, active6, 0x1f00010030002001L, active7, 0xb004007cff000cL, active8, 0L);
      case 70:
         if ((active2 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 161;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x80L) != 0L)
         {
            jjmatchedKind = 199;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x800L, active6, 0x1000L, active7, 0x20L, active8, 0L);
      case 71:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000L, active7, 0L, active8, 0L);
      case 72:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x1f000000L, active2, 0L, active3, 0L, active4, 0x20018000L, active5, 0x1008000000000L, active6, 0L, active7, 0x40000180000000L, active8, 0L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0xc000000000030000L, active2, 0xc40000000008001L, active3, 0x300000200000000L, active4, 0x4000040000064000L, active5, 0x8000f0000000000L, active6, 0xe042000040000000L, active7, 0x380000000000L, active8, 0L);
      case 74:
         if ((active7 & 0x40L) != 0L)
         {
            jjmatchedKind = 454;
            jjmatchedPos = 1;
         }
         break;
      case 76:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000001cL, active2, 0x1010008L, active3, 0L, active4, 0x46000000L, active5, 0x2000L, active6, 0x10000L, active7, 0x180L, active8, 0L);
      case 77:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x400000000L, active3, 0x80000000000L, active4, 0L, active5, 0L, active6, 0x2000007c0L, active7, 0L, active8, 0L);
      case 78:
         if ((active2 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 163;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x100L) != 0L)
         {
            jjmatchedKind = 200;
            jjmatchedPos = 1;
         }
         else if ((active6 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 420;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x60L, active2, 0x7ff000000030L, active3, 0x200L, active4, 0x3e00000000001cL, active5, 0x400000000000L, active6, 0xe00400000000L, active7, 0x2000000000000L, active8, 0L);
      case 79:
         if ((active2 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 153;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x2L) != 0L)
         {
            jjmatchedKind = 193;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 250;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x7ffc0040000L, active2, 0x10820000840e0002L, active3, 0x100c00000004L, active4, 0x9c00400780080000L, active5, 0x500200000030400fL, active6, 0xb80e60800e0006L, active7, 0x1L, active8, 0L);
      case 80:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x200000000c00L, active4, 0x60L, active5, 0L, active6, 0L, active7, 0x200000000L, active8, 0L);
      case 81:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x3c00000000000L, active4, 0L, active5, 0xff8000000L, active6, 0L, active7, 0xc00000000L, active8, 0L);
      case 82:
         if ((active3 & 0x1000L) != 0L)
         {
            jjmatchedKind = 204;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 472;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 498;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x180000000080L, active2, 0x38100004L, active3, 0xf800000003f02000L, active4, 0x100001L, active5, 0x200100000008000L, active6, 0x4000L, active7, 0xc00000001000L, active8, 0L);
      case 83:
         if ((active1 & 0x100L) != 0L)
         {
            jjmatchedKind = 72;
            jjmatchedPos = 1;
         }
         else if ((active2 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 175;
            jjmatchedPos = 1;
         }
         else if ((active4 & 0x2L) != 0L)
         {
            jjmatchedKind = 257;
            jjmatchedPos = 1;
         }
         else if ((active6 & 0x100000L) != 0L)
         {
            jjmatchedKind = 404;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 473;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x600L, active2, 0x1000000000040L, active3, 0L, active4, 0x180L, active5, 0x800000000000L, active6, 0x200000L, active7, 0x8000000000000L, active8, 0L);
      case 84:
         if ((active1 & 0x800L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x2000003000000000L, active6, 0x8018L, active7, 0x3f000000000L, active8, 0L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x7e00000001000L, active2, 0x600000L, active3, 0xc00000400c038L, active4, 0x80000000000L, active5, 0x78000000000390L, active6, 0x8000400020L, active7, 0xe200L, active8, 0L);
      case 86:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000L, active2, 0L, active3, 0x10000L, active4, 0L, active5, 0x8000000000000000L, active6, 0L, active7, 0x10L, active8, 0L);
      case 88:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x1f80L, active3, 0L, active4, 0x200000000000L, active5, 0x400000000000000L, active6, 0x800000000L, active7, 0L, active8, 0L);
      case 89:
         if ((active1 & 0x80000L) != 0L)
         {
            jjmatchedKind = 83;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0x100800000000L, active5, 0x204000000000L, active6, 0L, active7, 0x2L, active8, 0L);
      case 95:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800L, active7, 0L, active8, 0L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0xf00000L, active2, 0x6308000040002000L, active3, 0x400000000e0000L, active4, 0x20c000f010003e00L, active5, 0x84000000401020L, active6, 0x1000107800000L, active7, 0xc00L, active8, 0L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0xc00000L, active5, 0x400L, active6, 0x8000000L, active7, 0L, active8, 0L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x8000000000000000L, active3, 0x3000000040L, active4, 0x1000000L, active5, 0x800040L, active6, 0x4000000000000L, active7, 0x1000000000000L, active8, 0L);
      case 100:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2L, active2, 0x100000000L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L, active8, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x3ff000000000c000L, active2, 0x34000000804000L, active3, 0x8007c1f8000001L, active4, 0x300830000200000L, active5, 0x1000000070f0000L, active6, 0x1f00010030002001L, active7, 0xb004007cff000cL, active8, 0L);
      case 102:
         if ((active2 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 161;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x80L) != 0L)
         {
            jjmatchedKind = 199;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x800L, active6, 0x1000L, active7, 0x20L, active8, 0L);
      case 103:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000L, active7, 0L, active8, 0L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x1f000000L, active2, 0L, active3, 0L, active4, 0x20018000L, active5, 0x1008000000000L, active6, 0L, active7, 0x40000180000000L, active8, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0xc000000000030000L, active2, 0xc40000000008001L, active3, 0x300000200000000L, active4, 0x4000040000064000L, active5, 0x8000f0000000000L, active6, 0xe042000040000000L, active7, 0x380000000000L, active8, 0L);
      case 106:
         if ((active7 & 0x40L) != 0L)
         {
            jjmatchedKind = 454;
            jjmatchedPos = 1;
         }
         break;
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000001cL, active2, 0x1010008L, active3, 0L, active4, 0x46000000L, active5, 0x2000L, active6, 0x10000L, active7, 0x180L, active8, 0L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x400000000L, active3, 0x80000000000L, active4, 0L, active5, 0L, active6, 0x2000007c0L, active7, 0L, active8, 0L);
      case 110:
         if ((active2 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 163;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x100L) != 0L)
         {
            jjmatchedKind = 200;
            jjmatchedPos = 1;
         }
         else if ((active6 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 420;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x60L, active2, 0x7ff000000030L, active3, 0x200L, active4, 0x3e00000000001cL, active5, 0x400000000000L, active6, 0xe00400000000L, active7, 0x2000000000000L, active8, 0L);
      case 111:
         if ((active2 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 153;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x2L) != 0L)
         {
            jjmatchedKind = 193;
            jjmatchedPos = 1;
         }
         else if ((active3 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 250;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x7ffc0040000L, active2, 0x10820000840e0002L, active3, 0x100c00000004L, active4, 0x9c00400780080000L, active5, 0x500200000030400fL, active6, 0xb80e60800e0006L, active7, 0x1L, active8, 0L);
      case 112:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x200000000c00L, active4, 0x60L, active5, 0L, active6, 0L, active7, 0x200000000L, active8, 0L);
      case 113:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x3c00000000000L, active4, 0L, active5, 0xff8000000L, active6, 0L, active7, 0xc00000000L, active8, 0L);
      case 114:
         if ((active3 & 0x1000L) != 0L)
         {
            jjmatchedKind = 204;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 472;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 498;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x180000000080L, active2, 0x38100004L, active3, 0xf800000003f02000L, active4, 0x100001L, active5, 0x200100000008000L, active6, 0x4000L, active7, 0xc00000001000L, active8, 0L);
      case 115:
         if ((active1 & 0x100L) != 0L)
         {
            jjmatchedKind = 72;
            jjmatchedPos = 1;
         }
         else if ((active2 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 175;
            jjmatchedPos = 1;
         }
         else if ((active4 & 0x2L) != 0L)
         {
            jjmatchedKind = 257;
            jjmatchedPos = 1;
         }
         else if ((active6 & 0x100000L) != 0L)
         {
            jjmatchedKind = 404;
            jjmatchedPos = 1;
         }
         else if ((active7 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 473;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x600L, active2, 0x1000000000040L, active3, 0L, active4, 0x180L, active5, 0x800000000000L, active6, 0x200000L, active7, 0x8000000000000L, active8, 0L);
      case 116:
         if ((active1 & 0x800L) != 0L)
         {
            jjmatchedKind = 75;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x2000003000000000L, active6, 0x8018L, active7, 0x3f000000000L, active8, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x7e00000001000L, active2, 0x600000L, active3, 0xc00000400c038L, active4, 0x80000000000L, active5, 0x78000000000390L, active6, 0x8000400020L, active7, 0xe200L, active8, 0L);
      case 118:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000L, active2, 0L, active3, 0x10000L, active4, 0L, active5, 0x8000000000000000L, active6, 0L, active7, 0x10L, active8, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0x1f80L, active3, 0L, active4, 0x200000000000L, active5, 0x400000000000000L, active6, 0x800000000L, active7, 0L, active8, 0L);
      case 121:
         if ((active1 & 0x80000L) != 0L)
         {
            jjmatchedKind = 83;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0x100800000000L, active5, 0x204000000000L, active6, 0L, active7, 0x2L, active8, 0L);
      case 124:
         if ((active8 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 538;
            jjmatchedPos = 1;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7, long old8, long active8)
{
   if (((active0 &= old0) | (active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7) | (active8 &= old8)) == 0L)
      return jjMoveNfa_0(0, 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 1);
   }
   switch(curChar)
   {
      case 50:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000L, active7, 0L);
      case 65:
         return jjMoveStringLiteralDfa3_0(active1, 0x401000004f000000L, active2, 0x10000008000000L, active3, 0x7800280018000000L, active4, 0x20200000L, active5, 0x803000800000L, active6, 0x4010000010000L, active7, 0x81003080014010L);
      case 66:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0x44000004000000L, active4, 0x80000000L, active5, 0L, active6, 0x20L, active7, 0L);
      case 67:
         if ((active1 & 0x200L) != 0L)
         {
            jjmatchedKind = 73;
            jjmatchedPos = 2;
         }
         else if ((active1 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 117;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0xc0000000000000L, active2, 0x1c0L, active3, 0x4000000000L, active4, 0xc02000800000000L, active5, 0x400001000000L, active6, 0x2018000d80000000L, active7, 0x4000000L);
      case 68:
         if ((active1 & 0x2L) != 0L)
         {
            jjmatchedKind = 65;
            jjmatchedPos = 2;
         }
         else if ((active1 & 0x20L) != 0L)
         {
            jjmatchedKind = 69;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x10L) != 0L)
         {
            jjmatchedKind = 132;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x20000L) != 0L)
         {
            jjmatchedKind = 209;
            jjmatchedPos = 2;
         }
         else if ((active4 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 319;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x80L) != 0L)
         {
            jjmatchedKind = 455;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x1000003000000020L, active3, 0x2000L, active4, 0x20L, active5, 0x3L, active6, 0x700000000000000L, active7, 0xc000000101L);
      case 69:
         if ((active1 & 0x80L) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 499;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x80010000000L, active2, 0x100000600L, active3, 0x310400L, active4, 0x100000001c080L, active5, 0x8001008000008000L, active6, 0L, active7, 0x20000200000000L);
      case 70:
         if ((active7 & 0x20L) != 0L)
         {
            jjmatchedKind = 453;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x20000L) != 0L)
         {
            jjmatchedKind = 465;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x700000000000000L, active2, 0x20000000000000L, active3, 0x20000000L, active4, 0x10000000000L, active5, 0x800L, active6, 0x10002000L, active7, 0x40000L);
      case 71:
         if ((active1 & 0x2000L) != 0L)
         {
            jjmatchedKind = 77;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x4000L, active2, 0L, active3, 0x200000000L, active4, 0x1000000000000000L, active5, 0L, active6, 0x40000000000000L, active7, 0L);
      case 72:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x8000000000000000L, active3, 0x1000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 73:
         if ((active5 & 0x2000L) != 0L)
         {
            jjmatchedKind = 333;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x2004000000800L, active3, 0x8000000001c00000L, active4, 0x400000010010cL, active5, 0L, active6, 0x4000L, active7, 0x40400000000000L);
      case 74:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400L, active6, 0L, active7, 0L);
      case 75:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x40000000000000L, active3, 0L, active4, 0x10L, active5, 0L, active6, 0L, active7, 0L);
      case 76:
         if ((active1 & 0x4L) != 0L)
         {
            jjmatchedKind = 66;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 238;
            jjmatchedPos = 2;
         }
         else if ((active6 & 0x40L) != 0L)
         {
            jjmatchedKind = 390;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x800000380000008L, active2, 0x202000L, active3, 0x3808440000218L, active4, 0x10000600L, active5, 0x4000ff81101c0L, active6, 0x2008020000782L, active7, 0x400000000L);
      case 77:
         if ((active3 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 243;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x400000000L, active2, 0x400000000L, active3, 0x380100000000020L, active4, 0x80100000000L, active5, 0x8000f0000000230L, active6, 0x20000L, active7, 0L);
      case 78:
         if ((active2 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 186;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x1f800010000L, active2, 0x800008000400000L, active3, 0L, active4, 0x4140900600020000L, active5, 0x5000004000000004L, active6, 0xc0a0100000040000L, active7, 0x380000080000L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active1, 0x100020000000L, active2, 0x1000031110004L, active3, 0x2000000L, active4, 0x8000044000000L, active5, 0x202000000000000L, active6, 0x800L, active7, 0x9000L);
      case 80:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x10000000000L, active3, 0L, active4, 0x40L, active5, 0x400200002020000L, active6, 0x400200080000L, active7, 0x100000L);
      case 81:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x30000000L);
      case 82:
         if ((active2 & 0x20000L) != 0L)
         {
            jjmatchedKind = 145;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x7e20000000000L, active2, 0x48000L, active3, 0x20000c0000L, active4, 0x80400000083800L, active5, 0x2078000004000008L, active6, 0x6040408018L, active7, 0x10010808002400L);
      case 83:
         if ((active4 & 0x400000L) != 0L)
         {
            jjmatchedKind = 278;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0xb000000000f00400L, active2, 0x8060000000009L, active3, 0x10030080000000L, active4, 0x800000L, active5, 0x45000L, active6, 0x800000000000000L, active7, 0x2000000600802L);
      case 84:
         if ((active1 & 0x20000L) != 0L)
         {
            jjmatchedKind = 81;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x800000L) != 0L)
         {
            jjmatchedKind = 151;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 171;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x4L) != 0L)
         {
            jjmatchedKind = 194;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 234;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x200L) != 0L)
         {
            jjmatchedKind = 457;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x49010L, active2, 0x6100700004005000L, active3, 0xc840L, active4, 0x10227001040000L, active5, 0x180000000080000L, active6, 0x1000800000001001L, active7, 0x40800000L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active1, 0x40000000000L, active2, 0x80080002L, active3, 0L, active4, 0x1L, active5, 0x100000000000L, active6, 0xe0000000000L, active7, 0x800100000000L);
      case 86:
         if ((active4 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 298;
            jjmatchedPos = 2;
         }
         else if ((active6 & 0x200000L) != 0L)
         {
            jjmatchedKind = 405;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x40000000L, active3, 0x100000000L, active4, 0x220000000000000L, active5, 0x400000L, active6, 0x1000000000000L, active7, 0L);
      case 87:
         if ((active5 & 0x200000L) != 0L)
         {
            jjmatchedKind = 341;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x4L) != 0L)
         {
            jjmatchedKind = 450;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x80000000000000L, active3, 0x800000000L, active4, 0x2000000L, active5, 0L, active6, 0x4L, active7, 0x8L);
      case 88:
         if ((active2 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 185;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0x1L, active4, 0x2000000000000000L, active5, 0L, active6, 0L, active7, 0x40000000000L);
      case 89:
         if ((active1 & 0x40L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 178;
            jjmatchedPos = 2;
         }
         else if ((active4 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 295;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x7800000L, active7, 0x20000000000L);
      case 95:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x200000000000L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active1, 0x401000004f000000L, active2, 0x10000008000000L, active3, 0x7800280018000000L, active4, 0x20200000L, active5, 0x803000800000L, active6, 0x4010000010000L, active7, 0x81003080014010L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0x44000004000000L, active4, 0x80000000L, active5, 0L, active6, 0x20L, active7, 0L);
      case 99:
         if ((active1 & 0x200L) != 0L)
         {
            jjmatchedKind = 73;
            jjmatchedPos = 2;
         }
         else if ((active1 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 117;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0xc0000000000000L, active2, 0x1c0L, active3, 0x4000000000L, active4, 0xc02000800000000L, active5, 0x400001000000L, active6, 0x2018000d80000000L, active7, 0x4000000L);
      case 100:
         if ((active1 & 0x2L) != 0L)
         {
            jjmatchedKind = 65;
            jjmatchedPos = 2;
         }
         else if ((active1 & 0x20L) != 0L)
         {
            jjmatchedKind = 69;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x10L) != 0L)
         {
            jjmatchedKind = 132;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x20000L) != 0L)
         {
            jjmatchedKind = 209;
            jjmatchedPos = 2;
         }
         else if ((active4 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 319;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x80L) != 0L)
         {
            jjmatchedKind = 455;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x1000003000000020L, active3, 0x2000L, active4, 0x20L, active5, 0x3L, active6, 0x700000000000000L, active7, 0xc000000101L);
      case 101:
         if ((active1 & 0x80L) != 0L)
         {
            jjmatchedKind = 71;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 499;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x80010000000L, active2, 0x100000600L, active3, 0x310400L, active4, 0x100000001c080L, active5, 0x8001008000008000L, active6, 0L, active7, 0x20000200000000L);
      case 102:
         if ((active7 & 0x20L) != 0L)
         {
            jjmatchedKind = 453;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x20000L) != 0L)
         {
            jjmatchedKind = 465;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x700000000000000L, active2, 0x20000000000000L, active3, 0x20000000L, active4, 0x10000000000L, active5, 0x800L, active6, 0x10002000L, active7, 0x40000L);
      case 103:
         if ((active1 & 0x2000L) != 0L)
         {
            jjmatchedKind = 77;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x4000L, active2, 0L, active3, 0x200000000L, active4, 0x1000000000000000L, active5, 0L, active6, 0x40000000000000L, active7, 0L);
      case 104:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x8000000000000000L, active3, 0x1000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 105:
         if ((active5 & 0x2000L) != 0L)
         {
            jjmatchedKind = 333;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x2004000000800L, active3, 0x8000000001c00000L, active4, 0x400000010010cL, active5, 0L, active6, 0x4000L, active7, 0x40400000000000L);
      case 106:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400L, active6, 0L, active7, 0L);
      case 107:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x40000000000000L, active3, 0L, active4, 0x10L, active5, 0L, active6, 0L, active7, 0L);
      case 108:
         if ((active1 & 0x4L) != 0L)
         {
            jjmatchedKind = 66;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 238;
            jjmatchedPos = 2;
         }
         else if ((active6 & 0x40L) != 0L)
         {
            jjmatchedKind = 390;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x800000380000008L, active2, 0x202000L, active3, 0x3808440000218L, active4, 0x10000600L, active5, 0x4000ff81101c0L, active6, 0x2008020000782L, active7, 0x400000000L);
      case 109:
         if ((active3 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 243;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x400000000L, active2, 0x400000000L, active3, 0x380100000000020L, active4, 0x80100000000L, active5, 0x8000f0000000230L, active6, 0x20000L, active7, 0L);
      case 110:
         if ((active2 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 186;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x1f800010000L, active2, 0x800008000400000L, active3, 0L, active4, 0x4140900600020000L, active5, 0x5000004000000004L, active6, 0xc0a0100000040000L, active7, 0x380000080000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active1, 0x100020000000L, active2, 0x1000031110004L, active3, 0x2000000L, active4, 0x8000044000000L, active5, 0x202000000000000L, active6, 0x800L, active7, 0x9000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x10000000000L, active3, 0L, active4, 0x40L, active5, 0x400200002020000L, active6, 0x400200080000L, active7, 0x100000L);
      case 113:
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x30000000L);
      case 114:
         if ((active2 & 0x20000L) != 0L)
         {
            jjmatchedKind = 145;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x7e20000000000L, active2, 0x48000L, active3, 0x20000c0000L, active4, 0x80400000083800L, active5, 0x2078000004000008L, active6, 0x6040408018L, active7, 0x10010808002400L);
      case 115:
         if ((active4 & 0x400000L) != 0L)
         {
            jjmatchedKind = 278;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0xb000000000f00400L, active2, 0x8060000000009L, active3, 0x10030080000000L, active4, 0x800000L, active5, 0x45000L, active6, 0x800000000000000L, active7, 0x2000000600802L);
      case 116:
         if ((active1 & 0x20000L) != 0L)
         {
            jjmatchedKind = 81;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x800000L) != 0L)
         {
            jjmatchedKind = 151;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 171;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x4L) != 0L)
         {
            jjmatchedKind = 194;
            jjmatchedPos = 2;
         }
         else if ((active3 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 234;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x200L) != 0L)
         {
            jjmatchedKind = 457;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0x49010L, active2, 0x6100700004005000L, active3, 0xc840L, active4, 0x10227001040000L, active5, 0x180000000080000L, active6, 0x1000800000001001L, active7, 0x40800000L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active1, 0x40000000000L, active2, 0x80080002L, active3, 0L, active4, 0x1L, active5, 0x100000000000L, active6, 0xe0000000000L, active7, 0x800100000000L);
      case 118:
         if ((active4 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 298;
            jjmatchedPos = 2;
         }
         else if ((active6 & 0x200000L) != 0L)
         {
            jjmatchedKind = 405;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x40000000L, active3, 0x100000000L, active4, 0x220000000000000L, active5, 0x400000L, active6, 0x1000000000000L, active7, 0L);
      case 119:
         if ((active5 & 0x200000L) != 0L)
         {
            jjmatchedKind = 341;
            jjmatchedPos = 2;
         }
         else if ((active7 & 0x4L) != 0L)
         {
            jjmatchedKind = 450;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0x80000000000000L, active3, 0x800000000L, active4, 0x2000000L, active5, 0L, active6, 0x4L, active7, 0x8L);
      case 120:
         if ((active2 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 185;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0x1L, active4, 0x2000000000000000L, active5, 0L, active6, 0L, active7, 0x40000000000L);
      case 121:
         if ((active1 & 0x40L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 2;
         }
         else if ((active2 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 178;
            jjmatchedPos = 2;
         }
         else if ((active4 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 295;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x7800000L, active7, 0x20000000000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 2);
}
private int jjMoveStringLiteralDfa3_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 2);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 2);
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0x20L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 65:
         if ((active4 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 292;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 432;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x100080000010000L, active2, 0x8000000000010040L, active3, 0x40000000L, active4, 0x100002000020L, active5, 0x80000002000000L, active6, 0x8000000000010L, active7, 0x880400L);
      case 66:
         if ((active4 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 282;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 286;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0x1000002L, active3, 0L, active4, 0x800L, active5, 0x200L, active6, 0L, active7, 0x10001000000000L);
      case 67:
         if ((active1 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 124;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x200L) != 0L)
         {
            jjmatchedKind = 137;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0xa000000010300000L, active2, 0x100000000404400L, active3, 0xa00002000000L, active4, 0x200001000L, active5, 0x100000000009000L, active6, 0x2000000000L, active7, 0x200000000L);
      case 68:
         if ((active3 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 219;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0x10000000000000L, active3, 0L, active4, 0x20000L, active5, 0x8000000000000L, active6, 0x10000000000L, active7, 0x10000L);
      case 69:
         if ((active1 & 0x400000L) != 0L)
         {
            jjmatchedKind = 86;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x8L) != 0L)
         {
            jjmatchedKind = 131;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 182;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 236;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x1L) != 0L)
         {
            jjmatchedKind = 256;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 293;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8L) != 0L)
         {
            jjmatchedKind = 323;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x20L) != 0L)
         {
            jjmatchedKind = 325;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 360;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 365;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 382;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x2L) != 0L)
         {
            jjmatchedKind = 385;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x1L) != 0L)
         {
            jjmatchedKind = 448;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0xe00000000000410L, active2, 0x80369400041180L, active3, 0x301009020006060L, active4, 0x210824000000040L, active5, 0xe0000430400L, active6, 0x80000000d080L, active7, 0x240000L);
      case 71:
         if ((active5 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 380;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x4000000000000000L, active2, 0L, active3, 0L, active4, 0x11c0000000000000L, active5, 0x800000000000L, active6, 0xa0000000000000L, active7, 0x400000002000L);
      case 72:
         if ((active1 & 0x40000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x40000L) != 0L)
         {
            jjmatchedKind = 274;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 416;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x1000L, active2, 0L, active3, 0x200000000L, active4, 0L, active5, 0L, active6, 0x1000000000000000L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa4_0(active1, 0x40000000004000L, active2, 0x2000002040000000L, active3, 0x4000000000000800L, active4, 0x10001000000L, active5, 0x2800000004004003L, active6, 0x740000020000008L, active7, 0x2014400000000L);
      case 75:
         if ((active4 & 0x80000L) != 0L)
         {
            jjmatchedKind = 275;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 436;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 501;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0xc00000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 76:
         if ((active2 & 0x200000L) != 0L)
         {
            jjmatchedKind = 149;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x8L) != 0L)
         {
            jjmatchedKind = 195;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 220;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 370;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 433;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x900001c0000000L, active2, 0x1000000000000L, active3, 0x40080404000010L, active4, 0x810000000L, active5, 0x402000000900180L, active6, 0x408800000000L, active7, 0x20000100000L);
      case 77:
         if ((active2 & 0x100000L) != 0L)
         {
            jjmatchedKind = 148;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 255;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x400000000L, active2, 0L, active3, 0x400000L, active4, 0x80100000000L, active5, 0L, active6, 0x204000000000L, active7, 0L);
      case 78:
         if ((active2 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 177;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x400L) != 0L)
         {
            jjmatchedKind = 202;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 359;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 368;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x41800000000L, active2, 0x108080000L, active3, 0x3800000000000000L, active4, 0x1000000008110L, active5, 0x100000000000L, active6, 0L, active7, 0x800000000000L);
      case 79:
         if ((active2 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 154;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 174;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8L, active2, 0L, active3, 0x6100800000L, active4, 0x20000080000004L, active5, 0x404000000040L, active6, 0x100400002000L, active7, 0x8004000000L);
      case 80:
         if ((active2 & 0x4L) != 0L)
         {
            jjmatchedKind = 130;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0x80000000108000L, active4, 0L, active5, 0x10L, active6, 0x20100L, active7, 0x1000L);
      case 81:
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x8L, active5, 0L, active6, 0x200L, active7, 0x2L);
      case 82:
         if ((active1 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 88;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 159;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x200000L) != 0L)
         {
            jjmatchedKind = 277;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 383;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x3e2000e000000L, active2, 0L, active3, 0x10000L, active4, 0x2200020010080L, active5, 0x30001000000000L, active6, 0x20000e0010000800L, active7, 0x80000080004010L);
      case 83:
         if ((active3 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 227;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 478;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x4106020000000L, active2, 0xa800L, active3, 0x6030000200000L, active4, 0L, active5, 0x800L, active6, 0x804000008410420L, active7, 0x1000000000800L);
      case 84:
         if ((active1 & 0x800000L) != 0L)
         {
            jjmatchedKind = 87;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 179;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 181;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x1L) != 0L)
         {
            jjmatchedKind = 192;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 483;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 490;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8000000000L, active2, 0x4000000001L, active3, 0x100000800c0000L, active4, 0x4400400100000L, active5, 0x40002000040004L, active6, 0x240040000L, active7, 0x40002100008000L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active1, 0x200000000L, active2, 0x5800010030000000L, active3, 0L, active4, 0x8000000000600L, active5, 0x200000001080000L, active6, 0xc000000080000001L, active7, 0x30400000L);
      case 86:
         return jjMoveStringLiteralDfa4_0(active1, 0x10000000000L, active2, 0L, active3, 0x1000000L, active4, 0x6000000000800000L, active5, 0L, active6, 0L, active7, 0x8000000L);
      case 87:
         if ((active4 & 0x4000L) != 0L)
         {
            jjmatchedKind = 270;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 89:
         if ((active3 & 0x200L) != 0L)
         {
            jjmatchedKind = 201;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x80000L) != 0L)
         {
            jjmatchedKind = 403;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x2000L, active5, 0L, active6, 0L, active7, 0x380000000000L);
      case 95:
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0xff8000000L, active6, 0x7800004L, active7, 0x108L);
      case 97:
         if ((active4 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 292;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 432;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x100080000010000L, active2, 0x8000000000010040L, active3, 0x40000000L, active4, 0x100002000020L, active5, 0x80000002000000L, active6, 0x8000000000010L, active7, 0x880400L);
      case 98:
         if ((active4 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 282;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 286;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0x1000002L, active3, 0L, active4, 0x800L, active5, 0x200L, active6, 0L, active7, 0x10001000000000L);
      case 99:
         if ((active1 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 124;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x200L) != 0L)
         {
            jjmatchedKind = 137;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0xa000000010300000L, active2, 0x100000000404400L, active3, 0xa00002000000L, active4, 0x200001000L, active5, 0x100000000009000L, active6, 0x2000000000L, active7, 0x200000000L);
      case 100:
         if ((active3 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 219;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0x10000000000000L, active3, 0L, active4, 0x20000L, active5, 0x8000000000000L, active6, 0x10000000000L, active7, 0x10000L);
      case 101:
         if ((active1 & 0x400000L) != 0L)
         {
            jjmatchedKind = 86;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x8L) != 0L)
         {
            jjmatchedKind = 131;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 182;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 236;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x1L) != 0L)
         {
            jjmatchedKind = 256;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 293;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8L) != 0L)
         {
            jjmatchedKind = 323;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x20L) != 0L)
         {
            jjmatchedKind = 325;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 360;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 365;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 382;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x2L) != 0L)
         {
            jjmatchedKind = 385;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x1L) != 0L)
         {
            jjmatchedKind = 448;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0xe00000000000410L, active2, 0x80369400041180L, active3, 0x301009020006060L, active4, 0x210824000000040L, active5, 0xe0000430400L, active6, 0x80000000d080L, active7, 0x240000L);
      case 103:
         if ((active5 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 380;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x4000000000000000L, active2, 0L, active3, 0L, active4, 0x11c0000000000000L, active5, 0x800000000000L, active6, 0xa0000000000000L, active7, 0x400000002000L);
      case 104:
         if ((active1 & 0x40000L) != 0L)
         {
            jjmatchedKind = 82;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x40000L) != 0L)
         {
            jjmatchedKind = 274;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 416;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x1000L, active2, 0L, active3, 0x200000000L, active4, 0L, active5, 0L, active6, 0x1000000000000000L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active1, 0x40000000004000L, active2, 0x2000002040000000L, active3, 0x4000000000000800L, active4, 0x10001000000L, active5, 0x2800000004004003L, active6, 0x740000020000008L, active7, 0x2014400000000L);
      case 107:
         if ((active4 & 0x80000L) != 0L)
         {
            jjmatchedKind = 275;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 436;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 501;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0xc00000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 108:
         if ((active2 & 0x200000L) != 0L)
         {
            jjmatchedKind = 149;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x8L) != 0L)
         {
            jjmatchedKind = 195;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 220;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 370;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 433;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x900001c0000000L, active2, 0x1000000000000L, active3, 0x40080404000010L, active4, 0x810000000L, active5, 0x402000000900180L, active6, 0x408800000000L, active7, 0x20000100000L);
      case 109:
         if ((active2 & 0x100000L) != 0L)
         {
            jjmatchedKind = 148;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 255;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x400000000L, active2, 0L, active3, 0x400000L, active4, 0x80100000000L, active5, 0L, active6, 0x204000000000L, active7, 0L);
      case 110:
         if ((active2 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 177;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x400L) != 0L)
         {
            jjmatchedKind = 202;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 359;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 368;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x41800000000L, active2, 0x108080000L, active3, 0x3800000000000000L, active4, 0x1000000008110L, active5, 0x100000000000L, active6, 0L, active7, 0x800000000000L);
      case 111:
         if ((active2 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 154;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 174;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8L, active2, 0L, active3, 0x6100800000L, active4, 0x20000080000004L, active5, 0x404000000040L, active6, 0x100400002000L, active7, 0x8004000000L);
      case 112:
         if ((active2 & 0x4L) != 0L)
         {
            jjmatchedKind = 130;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0x80000000108000L, active4, 0L, active5, 0x10L, active6, 0x20100L, active7, 0x1000L);
      case 113:
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x8L, active5, 0L, active6, 0x200L, active7, 0x2L);
      case 114:
         if ((active1 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 88;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 159;
            jjmatchedPos = 3;
         }
         else if ((active4 & 0x200000L) != 0L)
         {
            jjmatchedKind = 277;
            jjmatchedPos = 3;
         }
         else if ((active5 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 383;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x3e2000e000000L, active2, 0L, active3, 0x10000L, active4, 0x2200020010080L, active5, 0x30001000000000L, active6, 0x20000e0010000800L, active7, 0x80000080004010L);
      case 115:
         if ((active3 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 227;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 478;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x4106020000000L, active2, 0xa800L, active3, 0x6030000200000L, active4, 0L, active5, 0x800L, active6, 0x804000008410420L, active7, 0x1000000000800L);
      case 116:
         if ((active1 & 0x800000L) != 0L)
         {
            jjmatchedKind = 87;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 179;
            jjmatchedPos = 3;
         }
         else if ((active2 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 181;
            jjmatchedPos = 3;
         }
         else if ((active3 & 0x1L) != 0L)
         {
            jjmatchedKind = 192;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 483;
            jjmatchedPos = 3;
         }
         else if ((active7 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 490;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8000000000L, active2, 0x4000000001L, active3, 0x100000800c0000L, active4, 0x4400400100000L, active5, 0x40002000040004L, active6, 0x240040000L, active7, 0x40002100008000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active1, 0x200000000L, active2, 0x5800010030000000L, active3, 0L, active4, 0x8000000000600L, active5, 0x200000001080000L, active6, 0xc000000080000001L, active7, 0x30400000L);
      case 118:
         return jjMoveStringLiteralDfa4_0(active1, 0x10000000000L, active2, 0L, active3, 0x1000000L, active4, 0x6000000000800000L, active5, 0L, active6, 0L, active7, 0x8000000L);
      case 119:
         if ((active4 & 0x4000L) != 0L)
         {
            jjmatchedKind = 270;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0x8000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 121:
         if ((active3 & 0x200L) != 0L)
         {
            jjmatchedKind = 201;
            jjmatchedPos = 3;
         }
         else if ((active6 & 0x80000L) != 0L)
         {
            jjmatchedKind = 403;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x2000L, active5, 0L, active6, 0L, active7, 0x380000000000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 3);
}
private int jjMoveStringLiteralDfa4_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 3);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 3);
   }
   switch(curChar)
   {
      case 40:
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x80L, active5, 0L, active6, 0L, active7, 0L);
      case 65:
         return jjMoveStringLiteralDfa5_0(active1, 0x80000186300000L, active2, 0x1000001000000L, active3, 0x500000L, active4, 0x6000200620800000L, active5, 0x408000004071080L, active6, 0x800404010000100L, active7, 0x100000L);
      case 66:
         if ((active5 & 0x40L) != 0L)
         {
            jjmatchedKind = 326;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x400000000L, active4, 0L, active5, 0x80000000000000L, active6, 0x20000000000000L, active7, 0x80000000000L);
      case 67:
         return jjMoveStringLiteralDfa5_0(active1, 0x8L, active2, 0x2000000000L, active3, 0x8000000000L, active4, 0L, active5, 0x100000000400L, active6, 0L, active7, 0x800000000010L);
      case 68:
         if ((active2 & 0x80000L) != 0L)
         {
            jjmatchedKind = 147;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 482;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x400000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400000800L, active7, 0x100000000L);
      case 69:
         if ((active1 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 93;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x2000L) != 0L)
         {
            jjmatchedKind = 141;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 237;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 246;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x200L) != 0L)
         {
            jjmatchedKind = 265;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x10000L) != 0L)
         {
            jjmatchedKind = 272;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x100000L) != 0L)
         {
            jjmatchedKind = 276;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 291;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 311;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000L) != 0L)
         {
            jjmatchedKind = 343;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 367;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x8000L) != 0L)
         {
            jjmatchedKind = 399;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 421;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 434;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x2000L) != 0L)
         {
            jjmatchedKind = 461;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x8000L) != 0L)
         {
            jjmatchedKind = 463;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 479;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 489;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 496;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x3e31840008000L, active2, 0x20L, active3, 0x10000002200000L, active4, 0x1002000010008400L, active5, 0x32002000000a00L, active6, 0x210000040400L, active7, 0x40000038001000L);
      case 70:
         if ((active6 & 0x4000L) != 0L)
         {
            jjmatchedKind = 398;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x3L, active6, 0L, active7, 0L);
      case 71:
         if ((active4 & 0x100L) != 0L)
         {
            jjmatchedKind = 264;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x100000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2400000000000L);
      case 72:
         if ((active2 & 0x4000L) != 0L)
         {
            jjmatchedKind = 142;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 184;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x4L) != 0L)
         {
            jjmatchedKind = 322;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000L, active5, 0L, active6, 0x800000L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa5_0(active1, 0x8400000000L, active2, 0x10004000040001L, active3, 0x300050c0010L, active4, 0x4000100002800L, active5, 0x40000000008000L, active6, 0x10L, active7, 0x103200800800L);
      case 75:
         if ((active1 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 92;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x100000000L, active4, 0x20000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 76:
         if ((active4 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 287;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 313;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x10000000000000L, active2, 0x1000000000000002L, active3, 0x4000082000010000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x400002L);
      case 77:
         if ((active5 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 381;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x8L) != 0L)
         {
            jjmatchedKind = 387;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x40000200000000L, active2, 0L, active3, 0x1000000000L, active4, 0x100000000000L, active5, 0x400000000000L, active6, 0xa3000000L, active7, 0x80400L);
      case 78:
         if ((active1 & 0x4000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x4L) != 0L)
         {
            jjmatchedKind = 258;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 486;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x4000000000000000L, active2, 0x20040000000L, active3, 0x4000000000L, active4, 0x10000000000L, active5, 0x4000000000L, active6, 0x40000000000004L, active7, 0x4000000L);
      case 79:
         return jjMoveStringLiteralDfa5_0(active1, 0x8004000000001000L, active2, 0x2000000000000000L, active3, 0x80800000000800L, active4, 0x1020010L, active5, 0L, active6, 0x3000000000400000L, active7, 0x10000000000000L);
      case 80:
         if ((active2 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 156;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 488;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x200001c0L, active3, 0L, active4, 0L, active5, 0x200000000400000L, active6, 0L, active7, 0L);
      case 81:
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000L, active7, 0L);
      case 82:
         if ((active1 & 0x10L) != 0L)
         {
            jjmatchedKind = 68;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 167;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 183;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 191;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x2000L) != 0L)
         {
            jjmatchedKind = 205;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x4000L) != 0L)
         {
            jjmatchedKind = 206;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x800000L) != 0L)
         {
            jjmatchedKind = 215;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x40L) != 0L)
         {
            jjmatchedKind = 262;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x1000L) != 0L)
         {
            jjmatchedKind = 396;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x2600000000010400L, active2, 0x4000240000001000L, active3, 0x10000a0000020L, active4, 0x10c20000000000L, active5, 0x3080000L, active6, 0x900000022001L, active7, 0x40000L);
      case 83:
         if ((active1 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 108;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 314;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x10L) != 0L)
         {
            jjmatchedKind = 324;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x100L) != 0L)
         {
            jjmatchedKind = 328;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x10000L) != 0L)
         {
            jjmatchedKind = 400;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x10000L) != 0L)
         {
            jjmatchedKind = 464;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x3800000000000000L, active4, 0x800000000000000L, active5, 0xe0000000000L, active6, 0x4000000L, active7, 0L);
      case 84:
         if ((active1 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 106;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000L) != 0L)
         {
            jjmatchedKind = 143;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x10000L) != 0L)
         {
            jjmatchedKind = 144;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 155;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 168;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 225;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 307;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 356;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 379;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x200000L) != 0L)
         {
            jjmatchedKind = 469;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x800086000000000L, active2, 0x800000100400800L, active3, 0x6000040000040L, active4, 0x101004000000020L, active5, 0xff8004000L, active6, 0xc088000000000020L, active7, 0x200000004108L);
      case 85:
         return jjMoveStringLiteralDfa5_0(active1, 0x100000000000000L, active2, 0x400L, active3, 0x8000L, active4, 0x40000000000008L, active5, 0x100000000100000L, active6, 0x700000800000200L, active7, 0x8000000000L);
      case 88:
         if ((active2 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 164;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x80L, active7, 0L);
      case 89:
         if ((active4 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 299;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 414;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 417;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x2000000L, active5, 0L, active6, 0L, active7, 0L);
      case 90:
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x300000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa5_0(active1, 0x8000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0xe8000000000L, active7, 0x80000000000000L);
      case 97:
         return jjMoveStringLiteralDfa5_0(active1, 0x80000186300000L, active2, 0x1000001000000L, active3, 0x500000L, active4, 0x6000200620800000L, active5, 0x408000004071080L, active6, 0x800404010000100L, active7, 0x100000L);
      case 98:
         if ((active5 & 0x40L) != 0L)
         {
            jjmatchedKind = 326;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x400000000L, active4, 0L, active5, 0x80000000000000L, active6, 0x20000000000000L, active7, 0x80000000000L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active1, 0x8L, active2, 0x2000000000L, active3, 0x8000000000L, active4, 0L, active5, 0x100000000400L, active6, 0L, active7, 0x800000000010L);
      case 100:
         if ((active2 & 0x80000L) != 0L)
         {
            jjmatchedKind = 147;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 482;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x400000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400000800L, active7, 0x100000000L);
      case 101:
         if ((active1 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 93;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x2000L) != 0L)
         {
            jjmatchedKind = 141;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 237;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 246;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x200L) != 0L)
         {
            jjmatchedKind = 265;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x10000L) != 0L)
         {
            jjmatchedKind = 272;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x100000L) != 0L)
         {
            jjmatchedKind = 276;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 291;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 311;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000L) != 0L)
         {
            jjmatchedKind = 343;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 367;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x8000L) != 0L)
         {
            jjmatchedKind = 399;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 421;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 434;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x2000L) != 0L)
         {
            jjmatchedKind = 461;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x8000L) != 0L)
         {
            jjmatchedKind = 463;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 479;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 489;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 496;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x3e31840008000L, active2, 0x20L, active3, 0x10000002200000L, active4, 0x1002000010008400L, active5, 0x32002000000a00L, active6, 0x210000040400L, active7, 0x40000038001000L);
      case 102:
         if ((active6 & 0x4000L) != 0L)
         {
            jjmatchedKind = 398;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x3L, active6, 0L, active7, 0L);
      case 103:
         if ((active4 & 0x100L) != 0L)
         {
            jjmatchedKind = 264;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x100000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2400000000000L);
      case 104:
         if ((active2 & 0x4000L) != 0L)
         {
            jjmatchedKind = 142;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 184;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x4L) != 0L)
         {
            jjmatchedKind = 322;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000L, active5, 0L, active6, 0x800000L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active1, 0x8400000000L, active2, 0x10004000040001L, active3, 0x300050c0010L, active4, 0x4000100002800L, active5, 0x40000000008000L, active6, 0x10L, active7, 0x103200800800L);
      case 107:
         if ((active1 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 92;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x100000000L, active4, 0x20000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 108:
         if ((active4 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 287;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 313;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x10000000000000L, active2, 0x1000000000000002L, active3, 0x4000082000010000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x400002L);
      case 109:
         if ((active5 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 381;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x8L) != 0L)
         {
            jjmatchedKind = 387;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x40000200000000L, active2, 0L, active3, 0x1000000000L, active4, 0x100000000000L, active5, 0x400000000000L, active6, 0xa3000000L, active7, 0x80400L);
      case 110:
         if ((active1 & 0x4000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x4L) != 0L)
         {
            jjmatchedKind = 258;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 486;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x4000000000000000L, active2, 0x20040000000L, active3, 0x4000000000L, active4, 0x10000000000L, active5, 0x4000000000L, active6, 0x40000000000004L, active7, 0x4000000L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active1, 0x8004000000001000L, active2, 0x2000000000000000L, active3, 0x80800000000800L, active4, 0x1020010L, active5, 0L, active6, 0x3000000000400000L, active7, 0x10000000000000L);
      case 112:
         if ((active2 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 156;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 488;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0x200001c0L, active3, 0L, active4, 0L, active5, 0x200000000400000L, active6, 0L, active7, 0L);
      case 113:
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000L, active7, 0L);
      case 114:
         if ((active1 & 0x10L) != 0L)
         {
            jjmatchedKind = 68;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 167;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 183;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 191;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x2000L) != 0L)
         {
            jjmatchedKind = 205;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x4000L) != 0L)
         {
            jjmatchedKind = 206;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x800000L) != 0L)
         {
            jjmatchedKind = 215;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x40L) != 0L)
         {
            jjmatchedKind = 262;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x1000L) != 0L)
         {
            jjmatchedKind = 396;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x2600000000010400L, active2, 0x4000240000001000L, active3, 0x10000a0000020L, active4, 0x10c20000000000L, active5, 0x3080000L, active6, 0x900000022001L, active7, 0x40000L);
      case 115:
         if ((active1 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 108;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 314;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x10L) != 0L)
         {
            jjmatchedKind = 324;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x100L) != 0L)
         {
            jjmatchedKind = 328;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x10000L) != 0L)
         {
            jjmatchedKind = 400;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x10000L) != 0L)
         {
            jjmatchedKind = 464;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x3800000000000000L, active4, 0x800000000000000L, active5, 0xe0000000000L, active6, 0x4000000L, active7, 0L);
      case 116:
         if ((active1 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 106;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000L) != 0L)
         {
            jjmatchedKind = 143;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x10000L) != 0L)
         {
            jjmatchedKind = 144;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 155;
            jjmatchedPos = 4;
         }
         else if ((active2 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 168;
            jjmatchedPos = 4;
         }
         else if ((active3 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 225;
            jjmatchedPos = 4;
         }
         else if ((active4 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 307;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 356;
            jjmatchedPos = 4;
         }
         else if ((active5 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 379;
            jjmatchedPos = 4;
         }
         else if ((active7 & 0x200000L) != 0L)
         {
            jjmatchedKind = 469;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0x800086000000000L, active2, 0x800000100400800L, active3, 0x6000040000040L, active4, 0x101004000000020L, active5, 0xff8004000L, active6, 0xc088000000000020L, active7, 0x200000004108L);
      case 117:
         return jjMoveStringLiteralDfa5_0(active1, 0x100000000000000L, active2, 0x400L, active3, 0x8000L, active4, 0x40000000000008L, active5, 0x100000000100000L, active6, 0x700000800000200L, active7, 0x8000000000L);
      case 120:
         if ((active2 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 164;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x80L, active7, 0L);
      case 121:
         if ((active4 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 299;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 414;
            jjmatchedPos = 4;
         }
         else if ((active6 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 417;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x2000000L, active5, 0L, active6, 0L, active7, 0L);
      case 122:
         return jjMoveStringLiteralDfa5_0(active1, 0L, active2, 0L, active3, 0x300000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 4);
}
private int jjMoveStringLiteralDfa5_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 4);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 4);
   }
   switch(curChar)
   {
      case 41:
         if ((active4 & 0x80L) != 0L)
         {
            jjmatchedKind = 263;
            jjmatchedPos = 5;
         }
         break;
      case 65:
         if ((active3 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 228;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x40000000000008L, active2, 0x4000006000000000L, active3, 0x802000400050000L, active4, 0x44c00000001000L, active5, 0x82100002000000L, active6, 0L, active7, 0x800000000108L);
      case 66:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80L, active6, 0L, active7, 0L);
      case 67:
         if ((active3 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 218;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x1806000000L, active2, 0L, active3, 0L, active4, 0x200020000000L, active5, 0L, active6, 0x400000000000L, active7, 0x100000L);
      case 68:
         if ((active3 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 230;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 284;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 316;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 444;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x300000L, active2, 0L, active3, 0x800002000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x4000000L);
      case 69:
         if ((active1 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 107;
            jjmatchedPos = 5;
         }
         else if ((active1 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 123;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x2L) != 0L)
         {
            jjmatchedKind = 129;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x40L) != 0L)
         {
            jjmatchedKind = 134;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 187;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 188;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 224;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x8L) != 0L)
         {
            jjmatchedKind = 259;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x20L) != 0L)
         {
            jjmatchedKind = 261;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x2000L) != 0L)
         {
            jjmatchedKind = 397;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 428;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 435;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x80000L) != 0L)
         {
            jjmatchedKind = 467;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x8000L, active2, 0x100000000000L, active3, 0x20000000L, active4, 0x20010000000000L, active5, 0L, active6, 0xc080000084020a00L, active7, 0x600000044400L);
      case 70:
         if ((active3 & 0x10L) != 0L)
         {
            jjmatchedKind = 196;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x200000000L);
      case 71:
         if ((active2 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 158;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0x40000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000010000010L, active7, 0L);
      case 72:
         if ((active4 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 312;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x10L);
      case 73:
         return jjMoveStringLiteralDfa6_0(active1, 0x2000000000000000L, active2, 0x500400000L, active3, 0x40000800c0000020L, active4, 0x801104400000000L, active5, 0x600000001004001L, active6, 0x423000080L, active7, 0L);
      case 76:
         if ((active2 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 152;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 229;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x800000L) != 0L)
         {
            jjmatchedKind = 279;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x1000L) != 0L)
         {
            jjmatchedKind = 332;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 411;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x100000008000000L, active2, 0L, active3, 0x3000000001000000L, active4, 0x6000000000000000L, active5, 0x4000000L, active6, 0x20000000000000L, active7, 0x81000000000L);
      case 77:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0x2020000000000L, active5, 0x40402000000000L, active6, 0x700260000000000L, active7, 0x80000000000000L);
      case 78:
         if ((active1 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 97;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x800L) != 0L)
         {
            jjmatchedKind = 203;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 280;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x800000L) != 0L)
         {
            jjmatchedKind = 471;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x8003e08000000000L, active2, 0x2010000000001001L, active3, 0L, active4, 0x2800L, active5, 0x30000000080000L, active6, 0x40001L, active7, 0x2100030000800L);
      case 79:
         return jjMoveStringLiteralDfa6_0(active1, 0x4010000000000000L, active2, 0L, active3, 0x300030000000000L, active4, 0L, active5, 0x400000L, active6, 0x800000L, active7, 0x100000000L);
      case 80:
         if ((active5 & 0x100000L) != 0L)
         {
            jjmatchedKind = 340;
            jjmatchedPos = 5;
         }
         break;
      case 82:
         if ((active1 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 114;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x200L) != 0L)
         {
            jjmatchedKind = 329;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x20L) != 0L)
         {
            jjmatchedKind = 389;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 424;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 475;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x680016000001000L, active2, 0L, active3, 0x85000000700000L, active4, 0L, active5, 0x100000000040000L, active6, 0x800000400500L, active7, 0x1000L);
      case 83:
         if ((active2 & 0x800L) != 0L)
         {
            jjmatchedKind = 139;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x400L) != 0L)
         {
            jjmatchedKind = 266;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 281;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x20040000000L, active2, 0x220000000000L, active3, 0L, active4, 0L, active5, 0xff8018000L, active6, 0x2000080800000000L, active7, 0x50002000000000L);
      case 84:
         if ((active1 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 98;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x80L) != 0L)
         {
            jjmatchedKind = 135;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 170;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x8000L) != 0L)
         {
            jjmatchedKind = 207;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 231;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 289;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x400L) != 0L)
         {
            jjmatchedKind = 330;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x800L) != 0L)
         {
            jjmatchedKind = 331;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 422;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 438;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x400000L) != 0L)
         {
            jjmatchedKind = 470;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 487;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x180000400L, active2, 0x1000000000500L, active3, 0x80000L, active4, 0x100000000L, active5, 0x80e0000020000L, active6, 0x8000000000L, active7, 0L);
      case 85:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4L, active7, 0L);
      case 86:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x10000000008000L, active5, 0L, active6, 0L, active7, 0L);
      case 87:
         if ((active4 & 0x20000L) != 0L)
         {
            jjmatchedKind = 273;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x10L, active5, 0L, active6, 0L, active7, 0L);
      case 88:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0x20L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 89:
         if ((active1 & 0x10000L) != 0L)
         {
            jjmatchedKind = 80;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x2L) != 0L)
         {
            jjmatchedKind = 321;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000000L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0x20000000L, active3, 0x40L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2L);
      case 97:
         if ((active3 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 228;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x40000000000008L, active2, 0x4000006000000000L, active3, 0x802000400050000L, active4, 0x44c00000001000L, active5, 0x82100002000000L, active6, 0L, active7, 0x800000000108L);
      case 98:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80L, active6, 0L, active7, 0L);
      case 99:
         if ((active3 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 218;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x1806000000L, active2, 0L, active3, 0L, active4, 0x200020000000L, active5, 0L, active6, 0x400000000000L, active7, 0x100000L);
      case 100:
         if ((active3 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 230;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 284;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 316;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 444;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x300000L, active2, 0L, active3, 0x800002000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x4000000L);
      case 101:
         if ((active1 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 107;
            jjmatchedPos = 5;
         }
         else if ((active1 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 123;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x2L) != 0L)
         {
            jjmatchedKind = 129;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x40L) != 0L)
         {
            jjmatchedKind = 134;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 187;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 188;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 224;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x8L) != 0L)
         {
            jjmatchedKind = 259;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x20L) != 0L)
         {
            jjmatchedKind = 261;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x2000L) != 0L)
         {
            jjmatchedKind = 397;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 428;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 435;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x80000L) != 0L)
         {
            jjmatchedKind = 467;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x8000L, active2, 0x100000000000L, active3, 0x20000000L, active4, 0x20010000000000L, active5, 0L, active6, 0xc080000084020a00L, active7, 0x600000044400L);
      case 102:
         if ((active3 & 0x10L) != 0L)
         {
            jjmatchedKind = 196;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x200000000L);
      case 103:
         if ((active2 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 158;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0x40000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000010000010L, active7, 0L);
      case 104:
         if ((active4 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 312;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x10L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active1, 0x2000000000000000L, active2, 0x500400000L, active3, 0x40000800c0000020L, active4, 0x801104400000000L, active5, 0x600000001004001L, active6, 0x423000080L, active7, 0L);
      case 108:
         if ((active2 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 152;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 229;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x800000L) != 0L)
         {
            jjmatchedKind = 279;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x1000L) != 0L)
         {
            jjmatchedKind = 332;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 411;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x100000008000000L, active2, 0L, active3, 0x3000000001000000L, active4, 0x6000000000000000L, active5, 0x4000000L, active6, 0x20000000000000L, active7, 0x81000000000L);
      case 109:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0x2020000000000L, active5, 0x40402000000000L, active6, 0x700260000000000L, active7, 0x80000000000000L);
      case 110:
         if ((active1 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 97;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x800L) != 0L)
         {
            jjmatchedKind = 203;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 280;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x800000L) != 0L)
         {
            jjmatchedKind = 471;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x8003e08000000000L, active2, 0x2010000000001001L, active3, 0L, active4, 0x2800L, active5, 0x30000000080000L, active6, 0x40001L, active7, 0x2100030000800L);
      case 111:
         return jjMoveStringLiteralDfa6_0(active1, 0x4010000000000000L, active2, 0L, active3, 0x300030000000000L, active4, 0L, active5, 0x400000L, active6, 0x800000L, active7, 0x100000000L);
      case 112:
         if ((active5 & 0x100000L) != 0L)
         {
            jjmatchedKind = 340;
            jjmatchedPos = 5;
         }
         break;
      case 114:
         if ((active1 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 114;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x200L) != 0L)
         {
            jjmatchedKind = 329;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x20L) != 0L)
         {
            jjmatchedKind = 389;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 424;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 475;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x680016000001000L, active2, 0L, active3, 0x85000000700000L, active4, 0L, active5, 0x100000000040000L, active6, 0x800000400500L, active7, 0x1000L);
      case 115:
         if ((active2 & 0x800L) != 0L)
         {
            jjmatchedKind = 139;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x400L) != 0L)
         {
            jjmatchedKind = 266;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 281;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x20040000000L, active2, 0x220000000000L, active3, 0L, active4, 0L, active5, 0xff8018000L, active6, 0x2000080800000000L, active7, 0x50002000000000L);
      case 116:
         if ((active1 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 98;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x80L) != 0L)
         {
            jjmatchedKind = 135;
            jjmatchedPos = 5;
         }
         else if ((active2 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 170;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x8000L) != 0L)
         {
            jjmatchedKind = 207;
            jjmatchedPos = 5;
         }
         else if ((active3 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 231;
            jjmatchedPos = 5;
         }
         else if ((active4 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 289;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x400L) != 0L)
         {
            jjmatchedKind = 330;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x800L) != 0L)
         {
            jjmatchedKind = 331;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 422;
            jjmatchedPos = 5;
         }
         else if ((active6 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 438;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x400000L) != 0L)
         {
            jjmatchedKind = 470;
            jjmatchedPos = 5;
         }
         else if ((active7 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 487;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0x180000400L, active2, 0x1000000000500L, active3, 0x80000L, active4, 0x100000000L, active5, 0x80e0000020000L, active6, 0x8000000000L, active7, 0L);
      case 117:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4L, active7, 0L);
      case 118:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x10000000008000L, active5, 0L, active6, 0L, active7, 0L);
      case 119:
         if ((active4 & 0x20000L) != 0L)
         {
            jjmatchedKind = 273;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x10L, active5, 0L, active6, 0L, active7, 0L);
      case 120:
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0x20L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 121:
         if ((active1 & 0x10000L) != 0L)
         {
            jjmatchedKind = 80;
            jjmatchedPos = 5;
         }
         else if ((active5 & 0x2L) != 0L)
         {
            jjmatchedKind = 321;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 5);
}
private int jjMoveStringLiteralDfa6_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 5);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 5);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa7_0(active1, 0x200006000000000L, active2, 0x2000000400001000L, active3, 0x3080000000000000L, active4, 0x10000000000800L, active5, 0xe0000020000L, active6, 0L, active7, 0x10L);
      case 66:
         return jjMoveStringLiteralDfa7_0(active1, 0x2000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000000L, active7, 0x108L);
      case 67:
         if ((active3 & 0x20L) != 0L)
         {
            jjmatchedKind = 197;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 300;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x10000040000000L, active2, 0x20000001L, active3, 0x800000480000000L, active4, 0L, active5, 0L, active6, 0x5000000L, active7, 0x10000000L);
      case 68:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2L);
      case 69:
         if ((active1 & 0x100000L) != 0L)
         {
            jjmatchedKind = 84;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 95;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 119;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x400L) != 0L)
         {
            jjmatchedKind = 138;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x100000L) != 0L)
         {
            jjmatchedKind = 212;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 239;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x10000L) != 0L)
         {
            jjmatchedKind = 336;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 371;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 374;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 430;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x100000L) != 0L)
         {
            jjmatchedKind = 468;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 500;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x400000008200000L, active2, 0x200000000020L, active3, 0x1000000L, active4, 0x2000000008000L, active5, 0x2000000001L, active6, 0x2800088000000000L, active7, 0x2000000000000L);
      case 71:
         if ((active2 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 180;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x2000L) != 0L)
         {
            jjmatchedKind = 269;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x800L) != 0L)
         {
            jjmatchedKind = 459;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x40000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 72:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x10L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa7_0(active1, 0x100001400L, active2, 0x1020000000100L, active3, 0x4000000080000L, active4, 0x20000000000L, active5, 0x400ffc408000L, active6, 0x200060800000401L, active7, 0x1200000000L);
      case 76:
         if ((active1 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 118;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 190;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x40000L) != 0L)
         {
            jjmatchedKind = 210;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 306;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0x4000000000L, active3, 0x40L, active4, 0L, active5, 0x80L, active6, 0L, active7, 0L);
      case 77:
         if ((active5 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 358;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x4000000000L, active5, 0L, active6, 0x10000004L, active7, 0L);
      case 78:
         if ((active1 & 0x8000L) != 0L)
         {
            jjmatchedKind = 79;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x40000L) != 0L)
         {
            jjmatchedKind = 146;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 232;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x10L) != 0L)
         {
            jjmatchedKind = 260;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 302;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 369;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 378;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x8000000000000000L, active2, 0L, active3, 0x43000a0020000000L, active4, 0x400000000L, active5, 0x200000000000000L, active6, 0x482000000L, active7, 0x40000L);
      case 79:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0x400000L, active3, 0x1000000000000L, active4, 0L, active5, 0x4000L, active6, 0x20200000000000L, active7, 0x80080000000000L);
      case 80:
         return jjMoveStringLiteralDfa7_0(active1, 0x20000000000L, active2, 0L, active3, 0x10000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x40000000000000L);
      case 82:
         if ((active2 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 172;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x1000L) != 0L)
         {
            jjmatchedKind = 268;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 296;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 309;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x4000L) != 0L)
         {
            jjmatchedKind = 462;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 494;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0xa00L, active7, 0L);
      case 83:
         if ((active5 & 0x80000L) != 0L)
         {
            jjmatchedKind = 339;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x400000L) != 0L)
         {
            jjmatchedKind = 406;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x4000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000000L, active6, 0x20180L, active7, 0L);
      case 84:
         if ((active1 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 99;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 104;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 109;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 120;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 301;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x40000L) != 0L)
         {
            jjmatchedKind = 338;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x40000L) != 0L)
         {
            jjmatchedKind = 402;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 492;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x3c01006000008L, active2, 0x2100000000L, active3, 0x2000000000000L, active4, 0x1800120000000L, active5, 0x30100003000000L, active6, 0x400000020000000L, active7, 0x802020001400L);
      case 85:
         return jjMoveStringLiteralDfa7_0(active1, 0x8000000000L, active2, 0L, active3, 0x2000000L, active4, 0x6000000000000000L, active5, 0L, active6, 0x800000800000L, active7, 0L);
      case 86:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0x40200000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 87:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x100000000L);
      case 88:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x80000000000000L, active7, 0x200000000000L);
      case 89:
         if ((active3 & 0x400000L) != 0L)
         {
            jjmatchedKind = 214;
            jjmatchedPos = 6;
         }
         break;
      case 90:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x800000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0L, active6, 0xc000000000000000L, active7, 0x4000000L);
      case 97:
         return jjMoveStringLiteralDfa7_0(active1, 0x200006000000000L, active2, 0x2000000400001000L, active3, 0x3080000000000000L, active4, 0x10000000000800L, active5, 0xe0000020000L, active6, 0L, active7, 0x10L);
      case 98:
         return jjMoveStringLiteralDfa7_0(active1, 0x2000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000000L, active7, 0x108L);
      case 99:
         if ((active3 & 0x20L) != 0L)
         {
            jjmatchedKind = 197;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 300;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x10000040000000L, active2, 0x20000001L, active3, 0x800000480000000L, active4, 0L, active5, 0L, active6, 0x5000000L, active7, 0x10000000L);
      case 100:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2L);
      case 101:
         if ((active1 & 0x100000L) != 0L)
         {
            jjmatchedKind = 84;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 95;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 119;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x400L) != 0L)
         {
            jjmatchedKind = 138;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x100000L) != 0L)
         {
            jjmatchedKind = 212;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 239;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x10000L) != 0L)
         {
            jjmatchedKind = 336;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x8000000000000L) != 0L)
         {
            jjmatchedKind = 371;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 374;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 430;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x100000L) != 0L)
         {
            jjmatchedKind = 468;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 500;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x400000008200000L, active2, 0x200000000020L, active3, 0x1000000L, active4, 0x2000000008000L, active5, 0x2000000001L, active6, 0x2800088000000000L, active7, 0x2000000000000L);
      case 103:
         if ((active2 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 180;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x2000L) != 0L)
         {
            jjmatchedKind = 269;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x800L) != 0L)
         {
            jjmatchedKind = 459;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x40000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 104:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x10L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa7_0(active1, 0x100001400L, active2, 0x1020000000100L, active3, 0x4000000080000L, active4, 0x20000000000L, active5, 0x400ffc408000L, active6, 0x200060800000401L, active7, 0x1200000000L);
      case 108:
         if ((active1 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 118;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 190;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x40000L) != 0L)
         {
            jjmatchedKind = 210;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 306;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0x4000000000L, active3, 0x40L, active4, 0L, active5, 0x80L, active6, 0L, active7, 0L);
      case 109:
         if ((active5 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 358;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x4000000000L, active5, 0L, active6, 0x10000004L, active7, 0L);
      case 110:
         if ((active1 & 0x8000L) != 0L)
         {
            jjmatchedKind = 79;
            jjmatchedPos = 6;
         }
         else if ((active2 & 0x40000L) != 0L)
         {
            jjmatchedKind = 146;
            jjmatchedPos = 6;
         }
         else if ((active3 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 232;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x10L) != 0L)
         {
            jjmatchedKind = 260;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 302;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 369;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 378;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x8000000000000000L, active2, 0L, active3, 0x43000a0020000000L, active4, 0x400000000L, active5, 0x200000000000000L, active6, 0x482000000L, active7, 0x40000L);
      case 111:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0x400000L, active3, 0x1000000000000L, active4, 0L, active5, 0x4000L, active6, 0x20200000000000L, active7, 0x80080000000000L);
      case 112:
         return jjMoveStringLiteralDfa7_0(active1, 0x20000000000L, active2, 0L, active3, 0x10000L, active4, 0L, active5, 0L, active6, 0L, active7, 0x40000000000000L);
      case 114:
         if ((active2 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 172;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x1000L) != 0L)
         {
            jjmatchedKind = 268;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 296;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 309;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x4000L) != 0L)
         {
            jjmatchedKind = 462;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 494;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0xa00L, active7, 0L);
      case 115:
         if ((active5 & 0x80000L) != 0L)
         {
            jjmatchedKind = 339;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x400000L) != 0L)
         {
            jjmatchedKind = 406;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x4000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000000L, active6, 0x20180L, active7, 0L);
      case 116:
         if ((active1 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 99;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 104;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 109;
            jjmatchedPos = 6;
         }
         else if ((active1 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 120;
            jjmatchedPos = 6;
         }
         else if ((active4 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 301;
            jjmatchedPos = 6;
         }
         else if ((active5 & 0x40000L) != 0L)
         {
            jjmatchedKind = 338;
            jjmatchedPos = 6;
         }
         else if ((active6 & 0x40000L) != 0L)
         {
            jjmatchedKind = 402;
            jjmatchedPos = 6;
         }
         else if ((active7 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 492;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active1, 0x3c01006000008L, active2, 0x2100000000L, active3, 0x2000000000000L, active4, 0x1800120000000L, active5, 0x30100003000000L, active6, 0x400000020000000L, active7, 0x802020001400L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active1, 0x8000000000L, active2, 0L, active3, 0x2000000L, active4, 0x6000000000000000L, active5, 0L, active6, 0x800000800000L, active7, 0L);
      case 118:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0x40200000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 119:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x100000000L);
      case 120:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x80000000000000L, active7, 0x200000000000L);
      case 121:
         if ((active3 & 0x400000L) != 0L)
         {
            jjmatchedKind = 214;
            jjmatchedPos = 6;
         }
         break;
      case 122:
         return jjMoveStringLiteralDfa7_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x800000000000000L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 6);
}
private int jjMoveStringLiteralDfa7_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 6);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 6);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa8_0(active1, 0x10000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400L, active7, 0x40000000000000L);
      case 66:
         if ((active6 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 437;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 491;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x200000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000L, active6, 0x4L, active7, 0L);
      case 67:
         if ((active2 & 0x20L) != 0L)
         {
            jjmatchedKind = 133;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 481;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0x200000000000L, active3, 0x20000000L, active4, 0L, active5, 0L, active6, 0x20000a0000000000L, active7, 0x40000L);
      case 68:
         if ((active1 & 0x200000L) != 0L)
         {
            jjmatchedKind = 85;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 122;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 497;
            jjmatchedPos = 7;
         }
         break;
      case 69:
         if ((active1 & 0x8L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 94;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 103;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 125;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x200000L) != 0L)
         {
            jjmatchedKind = 213;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 222;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 241;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 294;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 310;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 315;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 317;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 318;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x80L) != 0L)
         {
            jjmatchedKind = 327;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 364;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 375;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x100L) != 0L)
         {
            jjmatchedKind = 392;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 476;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x8000000006000000L, active2, 0L, active3, 0x300000000000040L, active4, 0x800120000000L, active5, 0x100000000000000L, active6, 0x400000030000000L, active7, 0x800000000400L);
      case 71:
         if ((active3 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 254;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 377;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 418;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x1000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa8_0(active1, 0x7000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2020001000L);
      case 75:
         if ((active3 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 226;
            jjmatchedPos = 7;
         }
         break;
      case 76:
         if ((active2 & 0x1000L) != 0L)
         {
            jjmatchedKind = 140;
            jjmatchedPos = 7;
         }
         else if ((active2 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 189;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 308;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0x4000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000000L, active7, 0x108L);
      case 77:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0xe0000000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 78:
         if ((active2 & 0x400000L) != 0L)
         {
            jjmatchedKind = 150;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x4000L) != 0L)
         {
            jjmatchedKind = 334;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 480;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x8000000L, active2, 0L, active3, 0x4000000000000L, active4, 0x2020000000000L, active5, 0x2000400000L, active6, 0x200040000000001L, active7, 0x80000000000000L);
      case 79:
         return jjMoveStringLiteralDfa8_0(active1, 0x20100000400L, active2, 0x1002020000100L, active3, 0x80000L, active4, 0L, active5, 0x2008000L, active6, 0x4000000L, active7, 0L);
      case 80:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000L, active7, 0L);
      case 82:
         if ((active3 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 240;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x8000L) != 0L)
         {
            jjmatchedKind = 271;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x800000L) != 0L)
         {
            jjmatchedKind = 407;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x10L) != 0L)
         {
            jjmatchedKind = 452;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x80000002000000L, active4, 0x800L, active5, 0L, active6, 0x200001000000L, active7, 0L);
      case 83:
         if ((active3 & 0x10000L) != 0L)
         {
            jjmatchedKind = 208;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 290;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x1L) != 0L)
         {
            jjmatchedKind = 320;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x20000L) != 0L)
         {
            jjmatchedKind = 401;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000000000000L, active7, 0L);
      case 84:
         if ((active2 & 0x1L) != 0L)
         {
            jjmatchedKind = 128;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 223;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 235;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 415;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 439;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 493;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x4000000000000000L, active2, 0x20400000000L, active3, 0x3800000000000000L, active4, 0L, active5, 0x400000000000L, active6, 0x90L, active7, 0x1000000000L);
      case 85:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0L, active6, 0x2000000L, active7, 0x2L);
      case 86:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000L, active7, 0L);
      case 88:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000000L, active7, 0L);
      case 89:
         if ((active2 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 160;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 344;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x200L) != 0L)
         {
            jjmatchedKind = 393;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 90:
         return jjMoveStringLiteralDfa8_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa8_0(active1, 0x3c00000000000L, active2, 0L, active3, 0x20000000000L, active4, 0L, active5, 0x30000ff8000000L, active6, 0x800000000000800L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa8_0(active1, 0x10000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400L, active7, 0x40000000000000L);
      case 98:
         if ((active6 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 437;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 491;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x200000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000L, active6, 0x4L, active7, 0L);
      case 99:
         if ((active2 & 0x20L) != 0L)
         {
            jjmatchedKind = 133;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 481;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0x200000000000L, active3, 0x20000000L, active4, 0L, active5, 0L, active6, 0x20000a0000000000L, active7, 0x40000L);
      case 100:
         if ((active1 & 0x200000L) != 0L)
         {
            jjmatchedKind = 85;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 122;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 497;
            jjmatchedPos = 7;
         }
         break;
      case 101:
         if ((active1 & 0x8L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 94;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 103;
            jjmatchedPos = 7;
         }
         else if ((active1 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 125;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x200000L) != 0L)
         {
            jjmatchedKind = 213;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 222;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 241;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 294;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 310;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 315;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 317;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 318;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x80L) != 0L)
         {
            jjmatchedKind = 327;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 364;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 375;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x100L) != 0L)
         {
            jjmatchedKind = 392;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 476;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x8000000006000000L, active2, 0L, active3, 0x300000000000040L, active4, 0x800120000000L, active5, 0x100000000000000L, active6, 0x400000030000000L, active7, 0x800000000400L);
      case 103:
         if ((active3 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 254;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 377;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 418;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x1000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active1, 0x7000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x2020001000L);
      case 107:
         if ((active3 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 226;
            jjmatchedPos = 7;
         }
         break;
      case 108:
         if ((active2 & 0x1000L) != 0L)
         {
            jjmatchedKind = 140;
            jjmatchedPos = 7;
         }
         else if ((active2 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 189;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 308;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0x4000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x100000000000000L, active7, 0x108L);
      case 109:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0xe0000000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 110:
         if ((active2 & 0x400000L) != 0L)
         {
            jjmatchedKind = 150;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x4000L) != 0L)
         {
            jjmatchedKind = 334;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 480;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x8000000L, active2, 0L, active3, 0x4000000000000L, active4, 0x2020000000000L, active5, 0x2000400000L, active6, 0x200040000000001L, active7, 0x80000000000000L);
      case 111:
         return jjMoveStringLiteralDfa8_0(active1, 0x20100000400L, active2, 0x1002020000100L, active3, 0x80000L, active4, 0L, active5, 0x2008000L, active6, 0x4000000L, active7, 0L);
      case 112:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000L, active7, 0L);
      case 114:
         if ((active3 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 240;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x8000L) != 0L)
         {
            jjmatchedKind = 271;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x800000L) != 0L)
         {
            jjmatchedKind = 407;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x10L) != 0L)
         {
            jjmatchedKind = 452;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x80000002000000L, active4, 0x800L, active5, 0L, active6, 0x200001000000L, active7, 0L);
      case 115:
         if ((active3 & 0x10000L) != 0L)
         {
            jjmatchedKind = 208;
            jjmatchedPos = 7;
         }
         else if ((active4 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 290;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x1L) != 0L)
         {
            jjmatchedKind = 320;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x20000L) != 0L)
         {
            jjmatchedKind = 401;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000000000000L, active7, 0L);
      case 116:
         if ((active2 & 0x1L) != 0L)
         {
            jjmatchedKind = 128;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 223;
            jjmatchedPos = 7;
         }
         else if ((active3 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 235;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 415;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 439;
            jjmatchedPos = 7;
         }
         else if ((active7 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 493;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0x4000000000000000L, active2, 0x20400000000L, active3, 0x3800000000000000L, active4, 0L, active5, 0x400000000000L, active6, 0x90L, active7, 0x1000000000L);
      case 117:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0L, active6, 0x2000000L, active7, 0x2L);
      case 118:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000L, active7, 0L);
      case 120:
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x8000000000L, active7, 0L);
      case 121:
         if ((active2 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 160;
            jjmatchedPos = 7;
         }
         else if ((active5 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 344;
            jjmatchedPos = 7;
         }
         else if ((active6 & 0x200L) != 0L)
         {
            jjmatchedKind = 393;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 122:
         return jjMoveStringLiteralDfa8_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 7);
}
private int jjMoveStringLiteralDfa8_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 7);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 7);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa9_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0x20000000L);
      case 67:
         return jjMoveStringLiteralDfa9_0(active1, 0x8000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x40002000000000L);
      case 68:
         if ((active4 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 288;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 303;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x400000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000L, active6, 0L, active7, 0L);
      case 69:
         if ((active2 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 162;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 217;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 252;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 419;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x8L) != 0L)
         {
            jjmatchedKind = 451;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x100L) != 0L)
         {
            jjmatchedKind = 456;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0x21000000L, active4, 0L, active5, 0L, active6, 0x8000000000000004L, active7, 0x1000L);
      case 70:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 71:
         if ((active3 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 242;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x1L) != 0L)
         {
            jjmatchedKind = 384;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x8000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 72:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000L, active6, 0L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa9_0(active1, 0x4000000000000000L, active2, 0x20000000000L, active3, 0x2800000000000000L, active4, 0x20000000000L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4040000L);
      case 76:
         return jjMoveStringLiteralDfa9_0(active1, 0x200000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000L, active6, 0x800000000000c00L, active7, 0L);
      case 77:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x220000000L, active6, 0L, active7, 0x2L);
      case 78:
         if ((active1 & 0x400L) != 0L)
         {
            jjmatchedKind = 74;
            jjmatchedPos = 8;
         }
         else if ((active1 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 96;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x100L) != 0L)
         {
            jjmatchedKind = 136;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 176;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x80000L) != 0L)
         {
            jjmatchedKind = 211;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x8000L) != 0L)
         {
            jjmatchedKind = 335;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x26000000000L, active2, 0x20000000L, active3, 0x40L, active4, 0L, active5, 0x100000000000000L, active6, 0x14000000L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa9_0(active1, 0x1000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x2100080001000000L, active7, 0L);
      case 80:
         if ((active5 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 361;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0xc0000000000L, active6, 0L, active7, 0L);
      case 81:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 82:
         if ((active1 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 89;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 165;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 345;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 413;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x400L) != 0L)
         {
            jjmatchedKind = 458;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x4000000L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x10000000000000L, active6, 0x20000000000L, active7, 0L);
      case 83:
         if ((active6 & 0x80L) != 0L)
         {
            jjmatchedKind = 391;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0x20000010000000L, active6, 0L, active7, 0L);
      case 84:
         if ((active2 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 173;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 305;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x400000L) != 0L)
         {
            jjmatchedKind = 342;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 357;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 423;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 431;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 441;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x11800000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000000L, active6, 0x2000000L, active7, 0x80000000000000L);
      case 85:
         return jjMoveStringLiteralDfa9_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0L, active5, 0L, active6, 0x40000000000L, active7, 0L);
      case 87:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000L, active6, 0L, active7, 0L);
      case 88:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400000000000000L, active7, 0L);
      case 89:
         if ((active2 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 166;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 247;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x800L) != 0L)
         {
            jjmatchedKind = 267;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 429;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 484;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x800000000L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0x300000000000000L, active4, 0x1000000000000L, active5, 0L, active6, 0x10L, active7, 0x800000000000L);
      case 97:
         return jjMoveStringLiteralDfa9_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0x20000000L);
      case 99:
         return jjMoveStringLiteralDfa9_0(active1, 0x8000000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0x40002000000000L);
      case 100:
         if ((active4 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 288;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 303;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x400000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000L, active6, 0L, active7, 0L);
      case 101:
         if ((active2 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 162;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 217;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x1000000000000000L) != 0L)
         {
            jjmatchedKind = 252;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 419;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x8L) != 0L)
         {
            jjmatchedKind = 451;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x100L) != 0L)
         {
            jjmatchedKind = 456;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0x21000000L, active4, 0L, active5, 0L, active6, 0x8000000000000004L, active7, 0x1000L);
      case 102:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 103:
         if ((active3 & 0x4000000000000L) != 0L)
         {
            jjmatchedKind = 242;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x1L) != 0L)
         {
            jjmatchedKind = 384;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x8000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 104:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000L, active6, 0L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa9_0(active1, 0x4000000000000000L, active2, 0x20000000000L, active3, 0x2800000000000000L, active4, 0x20000000000L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4040000L);
      case 108:
         return jjMoveStringLiteralDfa9_0(active1, 0x200000000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000L, active6, 0x800000000000c00L, active7, 0L);
      case 109:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x220000000L, active6, 0L, active7, 0x2L);
      case 110:
         if ((active1 & 0x400L) != 0L)
         {
            jjmatchedKind = 74;
            jjmatchedPos = 8;
         }
         else if ((active1 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 96;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x100L) != 0L)
         {
            jjmatchedKind = 136;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 176;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x80000L) != 0L)
         {
            jjmatchedKind = 211;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x8000L) != 0L)
         {
            jjmatchedKind = 335;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x26000000000L, active2, 0x20000000L, active3, 0x40L, active4, 0L, active5, 0x100000000000000L, active6, 0x14000000L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa9_0(active1, 0x1000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x2100080001000000L, active7, 0L);
      case 112:
         if ((active5 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 361;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0xc0000000000L, active6, 0L, active7, 0L);
      case 113:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 114:
         if ((active1 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 89;
            jjmatchedPos = 8;
         }
         else if ((active2 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 165;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 345;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 413;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x400L) != 0L)
         {
            jjmatchedKind = 458;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x4000000L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x10000000000000L, active6, 0x20000000000L, active7, 0L);
      case 115:
         if ((active6 & 0x80L) != 0L)
         {
            jjmatchedKind = 391;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0x20000010000000L, active6, 0L, active7, 0L);
      case 116:
         if ((active2 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 173;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 305;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x400000L) != 0L)
         {
            jjmatchedKind = 342;
            jjmatchedPos = 8;
         }
         else if ((active5 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 357;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 423;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 431;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 441;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0x11800000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000000L, active6, 0x2000000L, active7, 0x80000000000000L);
      case 117:
         return jjMoveStringLiteralDfa9_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0L, active5, 0L, active6, 0x40000000000L, active7, 0L);
      case 119:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000L, active6, 0L, active7, 0L);
      case 120:
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400000000000000L, active7, 0L);
      case 121:
         if ((active2 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 166;
            jjmatchedPos = 8;
         }
         else if ((active3 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 247;
            jjmatchedPos = 8;
         }
         else if ((active4 & 0x800L) != 0L)
         {
            jjmatchedKind = 267;
            jjmatchedPos = 8;
         }
         else if ((active6 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 429;
            jjmatchedPos = 8;
         }
         else if ((active7 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 484;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x800000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 8);
}
private int jjMoveStringLiteralDfa9_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 8);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 8);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa10_0(active1, 0x400000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40080000000L, active6, 0x800L, active7, 0L);
      case 66:
         if ((active6 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 440;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0L);
      case 67:
         return jjMoveStringLiteralDfa10_0(active1, 0x4000000000000000L, active2, 0x20000000L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0xc000000000000000L, active7, 0x4000000L);
      case 68:
         if ((active6 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 410;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x20000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 69:
         if ((active1 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 116;
            jjmatchedPos = 9;
         }
         else if ((active1 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 121;
            jjmatchedPos = 9;
         }
         else if ((active5 & 0x20000L) != 0L)
         {
            jjmatchedKind = 337;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 409;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 502;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0x400910000000L, active6, 0L, active7, 0x800000000000L);
      case 71:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x40L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 72:
         if ((active7 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 503;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa10_0(active1, 0x1800000000000L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x20000000L, active6, 0x400L, active7, 0L);
      case 74:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x10L, active7, 0L);
      case 76:
         if ((active7 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 477;
            jjmatchedPos = 9;
         }
         break;
      case 77:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 78:
         if ((active1 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 100;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x2000080000000000L, active7, 0x40000L);
      case 79:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x2800000000000000L, active4, 0L, active5, 0x10000240000000L, active6, 0x800020000000000L, active7, 0L);
      case 80:
         if ((active7 & 0x2L) != 0L)
         {
            jjmatchedKind = 449;
            jjmatchedPos = 9;
         }
         break;
      case 82:
         if ((active6 & 0x4L) != 0L)
         {
            jjmatchedKind = 386;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 83:
         if ((active3 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 216;
            jjmatchedPos = 9;
         }
         else if ((active3 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 221;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x1000L) != 0L)
         {
            jjmatchedKind = 460;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 485;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0x20000000000L, active5, 0L, active6, 0x1000000L, active7, 0L);
      case 84:
         if ((active1 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 101;
            jjmatchedPos = 9;
         }
         else if ((active1 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 127;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 412;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 442;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x4008001000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0x40000000000L, active7, 0L);
      case 85:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 86:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0x20000000000L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa10_0(active1, 0x4000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa10_0(active1, 0x400000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40080000000L, active6, 0x800L, active7, 0L);
      case 98:
         if ((active6 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 440;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x4000000L, active6, 0L, active7, 0L);
      case 99:
         return jjMoveStringLiteralDfa10_0(active1, 0x4000000000000000L, active2, 0x20000000L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0xc000000000000000L, active7, 0x4000000L);
      case 100:
         if ((active6 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 410;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x20000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 101:
         if ((active1 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 116;
            jjmatchedPos = 9;
         }
         else if ((active1 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 121;
            jjmatchedPos = 9;
         }
         else if ((active5 & 0x20000L) != 0L)
         {
            jjmatchedKind = 337;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 409;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x40000000000000L) != 0L)
         {
            jjmatchedKind = 502;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x10000000000000L, active4, 0L, active5, 0x400910000000L, active6, 0L, active7, 0x800000000000L);
      case 103:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x40L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 104:
         if ((active7 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 503;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa10_0(active1, 0x1800000000000L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x20000000L, active6, 0x400L, active7, 0L);
      case 106:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x10L, active7, 0L);
      case 108:
         if ((active7 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 477;
            jjmatchedPos = 9;
         }
         break;
      case 109:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 110:
         if ((active1 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 100;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x2000080000000000L, active7, 0x40000L);
      case 111:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0x2800000000000000L, active4, 0L, active5, 0x10000240000000L, active6, 0x800020000000000L, active7, 0L);
      case 112:
         if ((active7 & 0x2L) != 0L)
         {
            jjmatchedKind = 449;
            jjmatchedPos = 9;
         }
         break;
      case 114:
         if ((active6 & 0x4L) != 0L)
         {
            jjmatchedKind = 386;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 115:
         if ((active3 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 216;
            jjmatchedPos = 9;
         }
         else if ((active3 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 221;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x1000L) != 0L)
         {
            jjmatchedKind = 460;
            jjmatchedPos = 9;
         }
         else if ((active7 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 485;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0x20000000000L, active5, 0L, active6, 0x1000000L, active7, 0L);
      case 116:
         if ((active1 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 101;
            jjmatchedPos = 9;
         }
         else if ((active1 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 127;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 412;
            jjmatchedPos = 9;
         }
         else if ((active6 & 0x400000000000000L) != 0L)
         {
            jjmatchedKind = 442;
            jjmatchedPos = 9;
         }
         return jjMoveStringLiteralDfa10_0(active1, 0x4008001000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0x40000000000L, active7, 0L);
      case 117:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 118:
         return jjMoveStringLiteralDfa10_0(active1, 0L, active2, 0x20000000000L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 9);
}
private int jjMoveStringLiteralDfa10_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 9);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 9);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0x20000000L, active3, 0L, active4, 0x1000000000000L, active5, 0xc08000000L, active6, 0L, active7, 0L);
      case 67:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0x800000000000000L, active7, 0L);
      case 68:
         if ((active5 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 366;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 427;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 445;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000000L, active6, 0L, active7, 0L);
      case 69:
         if ((active2 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 169;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 426;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0L, active5, 0x100000000L, active6, 0x1000000L, active7, 0L);
      case 71:
         if ((active7 & 0x40000L) != 0L)
         {
            jjmatchedKind = 466;
            jjmatchedPos = 10;
         }
         break;
      case 72:
         if ((active1 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 91;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0L, active7, 0L);
      case 73:
         return jjMoveStringLiteralDfa11_0(active1, 0x20000001000L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 76:
         return jjMoveStringLiteralDfa11_0(active1, 0x4000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x10000004000000L, active6, 0L, active7, 0L);
      case 77:
         return jjMoveStringLiteralDfa11_0(active1, 0x1800000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 78:
         if ((active3 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 251;
            jjmatchedPos = 10;
         }
         else if ((active3 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 253;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x220000000L, active6, 0L, active7, 0x800000000000L);
      case 79:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0L, active6, 0x8000000000000010L, active7, 0L);
      case 82:
         if ((active3 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 244;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 83:
         if ((active1 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 102;
            jjmatchedPos = 10;
         }
         else if ((active1 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 126;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 84:
         return jjMoveStringLiteralDfa11_0(active1, 0x400000000000L, active2, 0L, active3, 0x40L, active4, 0x20000000000L, active5, 0L, active6, 0x800L, active7, 0L);
      case 85:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000L, active6, 0L, active7, 0L);
      case 89:
         if ((active5 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 351;
            jjmatchedPos = 10;
         }
         break;
      case 90:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0x20000000L, active3, 0L, active4, 0x1000000000000L, active5, 0xc08000000L, active6, 0L, active7, 0L);
      case 99:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0x800000000000000L, active7, 0L);
      case 100:
         if ((active5 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 366;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 427;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 445;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000000L, active6, 0L, active7, 0L);
      case 101:
         if ((active2 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 169;
            jjmatchedPos = 10;
         }
         else if ((active6 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 426;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0x2000000000000L, active2, 0L, active3, 0x20000000000L, active4, 0L, active5, 0x100000000L, active6, 0x1000000L, active7, 0L);
      case 103:
         if ((active7 & 0x40000L) != 0L)
         {
            jjmatchedKind = 466;
            jjmatchedPos = 10;
         }
         break;
      case 104:
         if ((active1 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 91;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0L, active7, 0L);
      case 105:
         return jjMoveStringLiteralDfa11_0(active1, 0x20000001000L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 108:
         return jjMoveStringLiteralDfa11_0(active1, 0x4000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x10000004000000L, active6, 0L, active7, 0L);
      case 109:
         return jjMoveStringLiteralDfa11_0(active1, 0x1800000000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 110:
         if ((active3 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 251;
            jjmatchedPos = 10;
         }
         else if ((active3 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 253;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x220000000L, active6, 0L, active7, 0x800000000000L);
      case 111:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0L, active6, 0x8000000000000010L, active7, 0L);
      case 114:
         if ((active3 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 244;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 115:
         if ((active1 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 102;
            jjmatchedPos = 10;
         }
         else if ((active1 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 126;
            jjmatchedPos = 10;
         }
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 116:
         return jjMoveStringLiteralDfa11_0(active1, 0x400000000000L, active2, 0L, active3, 0x40L, active4, 0x20000000000L, active5, 0L, active6, 0x800L, active7, 0L);
      case 117:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x40000000L, active6, 0L, active7, 0L);
      case 121:
         if ((active5 & 0x80000000L) != 0L)
         {
            jjmatchedKind = 351;
            jjmatchedPos = 10;
         }
         break;
      case 122:
         return jjMoveStringLiteralDfa11_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x400L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 10);
}
private int jjMoveStringLiteralDfa11_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 10);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 10);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000000L, active7, 0L);
      case 67:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0x1000000L, active7, 0L);
      case 68:
         if ((active5 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 362;
            jjmatchedPos = 11;
         }
         else if ((active7 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 495;
            jjmatchedPos = 11;
         }
         break;
      case 69:
         if ((active1 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 110;
            jjmatchedPos = 11;
         }
         else if ((active1 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 111;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 346;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 372;
            jjmatchedPos = 11;
         }
         else if ((active6 & 0x400L) != 0L)
         {
            jjmatchedKind = 394;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0x1000004000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0x20000000000L, active7, 0L);
      case 70:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 72:
         if ((active3 & 0x40L) != 0L)
         {
            jjmatchedKind = 198;
            jjmatchedPos = 11;
         }
         break;
      case 73:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000000L, active5, 0L, active6, 0x10L, active7, 0L);
      case 75:
         if ((active5 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 352;
            jjmatchedPos = 11;
         }
         break;
      case 76:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 78:
         return jjMoveStringLiteralDfa12_0(active1, 0x20000000000L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0x8000000000000000L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa12_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 82:
         if ((active1 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 113;
            jjmatchedPos = 11;
         }
         else if ((active3 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 233;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 350;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 355;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 84:
         if ((active2 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 157;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x200000000L, active6, 0L, active7, 0L);
      case 85:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0x20000000L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000000L, active7, 0L);
      case 99:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0x1000000L, active7, 0L);
      case 100:
         if ((active5 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 362;
            jjmatchedPos = 11;
         }
         else if ((active7 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 495;
            jjmatchedPos = 11;
         }
         break;
      case 101:
         if ((active1 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 110;
            jjmatchedPos = 11;
         }
         else if ((active1 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 111;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 346;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x10000000000000L) != 0L)
         {
            jjmatchedKind = 372;
            jjmatchedPos = 11;
         }
         else if ((active6 & 0x400L) != 0L)
         {
            jjmatchedKind = 394;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0x1000004000000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0x20000000000L, active7, 0L);
      case 102:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x80000000000L, active6, 0L, active7, 0L);
      case 104:
         if ((active3 & 0x40L) != 0L)
         {
            jjmatchedKind = 198;
            jjmatchedPos = 11;
         }
         break;
      case 105:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000000L, active5, 0L, active6, 0x10L, active7, 0L);
      case 107:
         if ((active5 & 0x100000000L) != 0L)
         {
            jjmatchedKind = 352;
            jjmatchedPos = 11;
         }
         break;
      case 108:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 110:
         return jjMoveStringLiteralDfa12_0(active1, 0x20000000000L, active2, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0x8000000000000000L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa12_0(active1, 0x1000L, active2, 0L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 114:
         if ((active1 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 113;
            jjmatchedPos = 11;
         }
         else if ((active3 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 233;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 350;
            jjmatchedPos = 11;
         }
         else if ((active5 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 355;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0L, active7, 0L);
      case 116:
         if ((active2 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 157;
            jjmatchedPos = 11;
         }
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0L, active4, 0x20000000L, active5, 0x200000000L, active6, 0L, active7, 0L);
      case 117:
         return jjMoveStringLiteralDfa12_0(active1, 0L, active2, 0L, active3, 0x100000000000000L, active4, 0L, active5, 0x20000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 11);
}
private int jjMoveStringLiteralDfa12_0(long old1, long active1, long old2, long active2, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active2 &= old2) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 11);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 11);
   }
   switch(curChar)
   {
      case 67:
         if ((active4 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 297;
            jjmatchedPos = 12;
         }
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 68:
         if ((active6 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 447;
            jjmatchedPos = 12;
         }
         break;
      case 70:
         if ((active5 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 363;
            jjmatchedPos = 12;
         }
         break;
      case 71:
         if ((active1 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 105;
            jjmatchedPos = 12;
         }
         break;
      case 72:
         if ((active5 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 353;
            jjmatchedPos = 12;
         }
         break;
      case 73:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0L, active7, 0L);
      case 76:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000800L, active7, 0L);
      case 77:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0L, active7, 0L);
      case 78:
         if ((active1 & 0x1000L) != 0L)
         {
            jjmatchedKind = 76;
            jjmatchedPos = 12;
         }
         else if ((active6 & 0x10L) != 0L)
         {
            jjmatchedKind = 388;
            jjmatchedPos = 12;
         }
         return jjMoveStringLiteralDfa13_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x1000000L, active7, 0L);
      case 82:
         if ((active3 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 248;
            jjmatchedPos = 12;
         }
         break;
      case 83:
         return jjMoveStringLiteralDfa13_0(active1, 0x1000000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 84:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x420000000L, active6, 0L, active7, 0L);
      case 85:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 95:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 99:
         if ((active4 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 297;
            jjmatchedPos = 12;
         }
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 100:
         if ((active6 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 447;
            jjmatchedPos = 12;
         }
         break;
      case 102:
         if ((active5 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 363;
            jjmatchedPos = 12;
         }
         break;
      case 103:
         if ((active1 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 105;
            jjmatchedPos = 12;
         }
         break;
      case 104:
         if ((active5 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 353;
            jjmatchedPos = 12;
         }
         break;
      case 105:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0L, active7, 0L);
      case 108:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x800000000000800L, active7, 0L);
      case 109:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x20000000000000L, active6, 0L, active7, 0L);
      case 110:
         if ((active1 & 0x1000L) != 0L)
         {
            jjmatchedKind = 76;
            jjmatchedPos = 12;
         }
         else if ((active6 & 0x10L) != 0L)
         {
            jjmatchedKind = 388;
            jjmatchedPos = 12;
         }
         return jjMoveStringLiteralDfa13_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0x100000010000000L, active6, 0L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x1000000L, active7, 0L);
      case 114:
         if ((active3 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 248;
            jjmatchedPos = 12;
         }
         break;
      case 115:
         return jjMoveStringLiteralDfa13_0(active1, 0x1000000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 116:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x420000000L, active6, 0L, active7, 0L);
      case 117:
         return jjMoveStringLiteralDfa13_0(active1, 0L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 12);
}
private int jjMoveStringLiteralDfa13_0(long old1, long active1, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 12);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 12);
   }
   switch(curChar)
   {
      case 65:
         if ((active5 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 373;
            jjmatchedPos = 13;
         }
         break;
      case 67:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0L, active7, 0L);
      case 68:
         if ((active5 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 348;
            jjmatchedPos = 13;
         }
         break;
      case 69:
         if ((active5 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 349;
            jjmatchedPos = 13;
         }
         else if ((active6 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 443;
            jjmatchedPos = 13;
         }
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 71:
         return jjMoveStringLiteralDfa14_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 76:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 78:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0x1000000L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000800L, active7, 0L);
      case 83:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 84:
         return jjMoveStringLiteralDfa14_0(active1, 0x1000000000000L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 97:
         if ((active5 & 0x20000000000000L) != 0L)
         {
            jjmatchedKind = 373;
            jjmatchedPos = 13;
         }
         break;
      case 99:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0x20000000L, active5, 0L, active6, 0L, active7, 0L);
      case 100:
         if ((active5 & 0x10000000L) != 0L)
         {
            jjmatchedKind = 348;
            jjmatchedPos = 13;
         }
         break;
      case 101:
         if ((active5 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 349;
            jjmatchedPos = 13;
         }
         else if ((active6 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 443;
            jjmatchedPos = 13;
         }
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x400000000L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 103:
         return jjMoveStringLiteralDfa14_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 108:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 110:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0x1000000L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000800L, active7, 0L);
      case 115:
         return jjMoveStringLiteralDfa14_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 116:
         return jjMoveStringLiteralDfa14_0(active1, 0x1000000000000L, active3, 0x200000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 13);
}
private int jjMoveStringLiteralDfa14_0(long old1, long active1, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 13);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 13);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa15_0(active1, 0x1000000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 67:
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 68:
         if ((active6 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 408;
            jjmatchedPos = 14;
         }
         break;
      case 69:
         if ((active3 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 249;
            jjmatchedPos = 14;
         }
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x100000008000000L, active6, 0L, active7, 0L);
      case 78:
         if ((active6 & 0x800L) != 0L)
         {
            jjmatchedKind = 395;
            jjmatchedPos = 14;
         }
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 82:
         if ((active5 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 354;
            jjmatchedPos = 14;
         }
         break;
      case 83:
         if ((active4 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 285;
            jjmatchedPos = 14;
         }
         break;
      case 84:
         return jjMoveStringLiteralDfa15_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa15_0(active1, 0x1000000000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 99:
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 100:
         if ((active6 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 408;
            jjmatchedPos = 14;
         }
         break;
      case 101:
         if ((active3 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 249;
            jjmatchedPos = 14;
         }
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0x100000008000000L, active6, 0L, active7, 0L);
      case 110:
         if ((active6 & 0x800L) != 0L)
         {
            jjmatchedKind = 395;
            jjmatchedPos = 14;
         }
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0L, active5, 0L, active6, 0x20000000000L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa15_0(active1, 0L, active3, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 114:
         if ((active5 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 354;
            jjmatchedPos = 14;
         }
         break;
      case 115:
         if ((active4 & 0x20000000L) != 0L)
         {
            jjmatchedKind = 285;
            jjmatchedPos = 14;
         }
         break;
      case 116:
         return jjMoveStringLiteralDfa15_0(active1, 0x4000000L, active3, 0L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 14);
}
private int jjMoveStringLiteralDfa15_0(long old1, long active1, long old3, long active3, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active3 &= old3) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 14);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 14);
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa16_0(active1, 0L, active4, 0x1000000000000L, active5, 0x100000008000000L, active6, 0L, active7, 0L);
      case 68:
         if ((active6 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 425;
            jjmatchedPos = 15;
         }
         break;
      case 72:
         if ((active1 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 90;
            jjmatchedPos = 15;
         }
         break;
      case 77:
         return jjMoveStringLiteralDfa16_0(active1, 0x1000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 79:
         return jjMoveStringLiteralDfa16_0(active1, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 99:
         return jjMoveStringLiteralDfa16_0(active1, 0L, active4, 0x1000000000000L, active5, 0x100000008000000L, active6, 0L, active7, 0L);
      case 100:
         if ((active6 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 425;
            jjmatchedPos = 15;
         }
         break;
      case 104:
         if ((active1 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 90;
            jjmatchedPos = 15;
         }
         break;
      case 109:
         return jjMoveStringLiteralDfa16_0(active1, 0x1000000000000L, active4, 0L, active5, 0L, active6, 0L, active7, 0L);
      case 111:
         return jjMoveStringLiteralDfa16_0(active1, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 15);
}
private int jjMoveStringLiteralDfa16_0(long old1, long active1, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 15);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 15);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 78:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 79:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 80:
         if ((active1 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 112;
            jjmatchedPos = 16;
         }
         break;
      case 84:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0L, active7, 0L);
      case 97:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0x1000000000000L, active5, 0L, active6, 0L, active7, 0L);
      case 110:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0L, active6, 0x4000000000000000L, active7, 0x4000000L);
      case 111:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 112:
         if ((active1 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 112;
            jjmatchedPos = 16;
         }
         break;
      case 116:
         return jjMoveStringLiteralDfa17_0(active1, 0L, active4, 0L, active5, 0x100000000000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 16);
}
private int jjMoveStringLiteralDfa17_0(long old1, long active1, long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active1 &= old1) | (active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 16);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 16);
   }
   switch(curChar)
   {
      case 68:
         if ((active6 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 446;
            jjmatchedPos = 17;
         }
         else if ((active7 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 474;
            jjmatchedPos = 17;
         }
         break;
      case 73:
         return jjMoveStringLiteralDfa18_0(active4, 0L, active5, 0x100000000000000L, active6, 0L, active7, 0L);
      case 76:
         if ((active4 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 304;
            jjmatchedPos = 17;
         }
         break;
      case 78:
         return jjMoveStringLiteralDfa18_0(active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      case 100:
         if ((active6 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 446;
            jjmatchedPos = 17;
         }
         else if ((active7 & 0x4000000L) != 0L)
         {
            jjmatchedKind = 474;
            jjmatchedPos = 17;
         }
         break;
      case 105:
         return jjMoveStringLiteralDfa18_0(active4, 0L, active5, 0x100000000000000L, active6, 0L, active7, 0L);
      case 108:
         if ((active4 & 0x1000000000000L) != 0L)
         {
            jjmatchedKind = 304;
            jjmatchedPos = 17;
         }
         break;
      case 110:
         return jjMoveStringLiteralDfa18_0(active4, 0L, active5, 0x8000000L, active6, 0L, active7, 0L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 17);
}
private int jjMoveStringLiteralDfa18_0(long old4, long active4, long old5, long active5, long old6, long active6, long old7, long active7)
{
   if (((active4 &= old4) | (active5 &= old5) | (active6 &= old6) | (active7 &= old7)) == 0L)
      return jjMoveNfa_0(0, 17);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 17);
   }
   switch(curChar)
   {
      case 68:
         if ((active5 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 347;
            jjmatchedPos = 18;
         }
         break;
      case 79:
         return jjMoveStringLiteralDfa19_0(active5, 0x100000000000000L);
      case 100:
         if ((active5 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 347;
            jjmatchedPos = 18;
         }
         break;
      case 111:
         return jjMoveStringLiteralDfa19_0(active5, 0x100000000000000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 18);
}
private int jjMoveStringLiteralDfa19_0(long old5, long active5)
{
   if (((active5 &= old5)) == 0L)
      return jjMoveNfa_0(0, 18);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 18);
   }
   switch(curChar)
   {
      case 78:
         if ((active5 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 376;
            jjmatchedPos = 19;
         }
         break;
      case 110:
         if ((active5 & 0x100000000000000L) != 0L)
         {
            jjmatchedKind = 376;
            jjmatchedPos = 19;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 19);
}
static final long[] jjbitVec5 = {
   0x0L, 0xffffffffffffc000L, 0xfffff0007fffffffL, 0x12000000007fffffL
};
static final long[] jjbitVec6 = {
   0x0L, 0x0L, 0x420040000000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xfc3fffffffffffffL
};
static final long[] jjbitVec8 = {
   0xffffffL, 0xffffffffffff0000L, 0xf9ff01ffffffffffL, 0x1f00030003L
};
static final long[] jjbitVec9 = {
   0x0L, 0x400000000000000L, 0xfffffffbffffd740L, 0xffffd547f7fffL
};
static final long[] jjbitVec10 = {
   0xffffffffffffdffeL, 0xffffffffdffeffffL, 0xffffffffffff0003L, 0x33fcfffffff199fL
};
static final long[] jjbitVec11 = {
   0xfffe000000000000L, 0xfffffffe027fffffL, 0xffL, 0x707ffffff0000L
};
static final long[] jjbitVec12 = {
   0x7fffffe00000000L, 0xfffe0000000007ffL, 0x7cffffffffffffffL, 0x60002f7fffL
};
static final long[] jjbitVec13 = {
   0x23ffffffffffffe0L, 0x3ff000000L, 0x3c5fdfffff99fe0L, 0x30003b0000000L
};
static final long[] jjbitVec14 = {
   0x36dfdfffff987e0L, 0x1c00005e000000L, 0x23edfdfffffbafe0L, 0x100000000L
};
static final long[] jjbitVec15 = {
   0x23cdfdfffff99fe0L, 0x3b0000000L, 0x3bfc718d63dc7e0L, 0x0L
};
static final long[] jjbitVec16 = {
   0x3effdfffffddfe0L, 0x300000000L, 0x3effdfffffddfe0L, 0x340000000L
};
static final long[] jjbitVec17 = {
   0x3fffdfffffddfe0L, 0x300000000L, 0x0L, 0x0L
};
static final long[] jjbitVec18 = {
   0xd7ffffffffffeL, 0x7fL, 0x200d6caefef02596L, 0x3000005fL
};
static final long[] jjbitVec19 = {
   0x0L, 0x3fffffffeffL, 0x0L, 0x0L
};
static final long[] jjbitVec20 = {
   0x0L, 0x0L, 0xffffffff00000000L, 0x7fffffffff003fL
};
static final long[] jjbitVec21 = {
   0xffffffffffffffffL, 0xffffffff83ffffffL, 0xffffff07ffffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec22 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffff0fffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec23 = {
   0xffffffff3f3fffffL, 0x3fffffffaaff3f3fL, 0x5fdfffffffffffffL, 0x1fdc1fff0fcf1fdcL
};
static final long[] jjbitVec24 = {
   0x0L, 0x8000000000000000L, 0x0L, 0x0L
};
static final long[] jjbitVec25 = {
   0x1fbfd503f2ffc84L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec26 = {
   0x3e000000000020L, 0xfffffffffffffffeL, 0xfffffffe781fffffL, 0x77ffffffffffffffL
};
static final long[] jjbitVec27 = {
   0xfffe1fffffffffe0L, 0xffffffffffffffffL, 0x7fffL, 0x0L
};
static final long[] jjbitVec28 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec29 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffL, 0x0L
};
static final long[] jjbitVec30 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec31 = {
   0x5f7ffdff80f8007fL, 0xffffffffffffffdbL, 0x3ffffffffffffL, 0xfffffffffff80000L
};
static final long[] jjbitVec32 = {
   0x3fffffffffffffffL, 0xffffffffffff0000L, 0xfffffffffffcffffL, 0xfff0000000000ffL
};
static final long[] jjbitVec33 = {
   0x0L, 0xffd7000000000000L, 0xffffffffffffffffL, 0x1fffffffffffffffL
};
static final long[] jjbitVec34 = {
   0x7fffffe00000000L, 0xffffffc007fffffeL, 0x7fffffffffffffffL, 0x1cfcfcfcL
};
static final long[] jjbitVec35 = {
   0x7fffffe00000000L, 0xfffe03ff000007ffL, 0x7cffffffffffffffL, 0x3ff0060002f7fffL
};
static final long[] jjbitVec36 = {
   0x23ffffffffffffe0L, 0xffc3ff000000L, 0x3c5fdfffff99fe0L, 0x3ffc3b0000000L
};
static final long[] jjbitVec37 = {
   0x36dfdfffff987e0L, 0x1cffc05e000000L, 0x23edfdfffffbafe0L, 0xffc100000000L
};
static final long[] jjbitVec38 = {
   0x23cdfdfffff99fe0L, 0xffc3b0000000L, 0x3bfc718d63dc7e0L, 0xff8000000000L
};
static final long[] jjbitVec39 = {
   0x3effdfffffddfe0L, 0xffc300000000L, 0x3effdfffffddfe0L, 0xffc340000000L
};
static final long[] jjbitVec40 = {
   0x3fffdfffffddfe0L, 0xffc300000000L, 0x0L, 0x0L
};
static final long[] jjbitVec41 = {
   0xd7ffffffffffeL, 0x3ff007fL, 0x200d6caefef02596L, 0x33ff005fL
};
static final long[] jjbitVec42 = {
   0x3ff00000000L, 0x3fffffffeffL, 0x0L, 0x0L
};
static final long[] jjbitVec43 = {
   0x7fffffe03ff0000L, 0xffffffc007fffffeL, 0x7fffffffffffffffL, 0x1cfcfcfcL
};
static final long[] jjbitVec44 = {
   0x1600L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec45 = {
   0x0L, 0xffc000000000L, 0x0L, 0xffc000000000L
};
static final long[] jjbitVec46 = {
   0x0L, 0x3ff00000000L, 0x0L, 0x3ff000000000000L
};
static final long[] jjbitVec47 = {
   0x0L, 0xffc000000000L, 0x0L, 0xff8000000000L
};
static final long[] jjbitVec48 = {
   0x0L, 0xffc000000000L, 0x0L, 0x0L
};
static final long[] jjbitVec49 = {
   0x0L, 0x3ff0000L, 0x0L, 0x3ff0000L
};
static final long[] jjbitVec50 = {
   0x3ff00000000L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec51 = {
   0x3ff0000L, 0x0L, 0x0L, 0x0L
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int strKind = jjmatchedKind;
   int strPos = jjmatchedPos;
   int seenUpto;
   input_stream.backup(seenUpto = curPos + 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { throw new Error("Internal Error"); }
   curPos = 0;
   int startsAt = 0;
   jjnewStateCnt = 34;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 554)
                        kind = 554;
                     jjCheckNAddStates(7, 14);
                  }
                  else if (curChar == 46)
                     jjCheckNAddTwoStates(32, 33);
                  else if (curChar == 39)
                     jjCheckNAddStates(15, 17);
                  else if (curChar == 36)
                     jjCheckNAdd(3);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 540)
                     kind = 540;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 2:
                  if (curChar == 36)
                     jjCheckNAdd(3);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 541)
                     kind = 541;
                  jjCheckNAdd(3);
                  break;
               case 7:
                  jjAddStates(18, 20);
                  break;
               case 9:
               case 10:
                  if (curChar == 39)
                     jjCheckNAddStates(15, 17);
                  break;
               case 11:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 12:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddStates(15, 17);
                  break;
               case 13:
                  if (curChar == 39 && kind > 557)
                     kind = 557;
                  break;
               case 15:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(16, 17);
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(16, 17);
                  break;
               case 17:
                  if (curChar == 39 && kind > 558)
                     kind = 558;
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 554)
                     kind = 554;
                  jjCheckNAddStates(7, 14);
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 554)
                     kind = 554;
                  jjCheckNAddTwoStates(19, 20);
                  break;
               case 20:
                  if (curChar != 46)
                     break;
                  if (kind > 554)
                     kind = 554;
                  jjCheckNAdd(21);
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 554)
                     kind = 554;
                  jjCheckNAdd(21);
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 555)
                     kind = 555;
                  jjCheckNAdd(22);
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(21, 23);
                  break;
               case 26:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 29:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(30);
                  break;
               case 30:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 559)
                     kind = 559;
                  jjCheckNAdd(30);
                  break;
               case 31:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(32, 33);
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 554)
                     kind = 554;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(33, 28);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 540)
                        kind = 540;
                     jjCheckNAdd(1);
                  }
                  else if (curChar == 96)
                     jjCheckNAddTwoStates(6, 7);
                  if ((0x100000001000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 540)
                     kind = 540;
                  jjCheckNAdd(1);
                  break;
               case 4:
                  if (curChar == 96)
                     jjCheckNAddTwoStates(6, 7);
                  break;
               case 5:
                  if (curChar == 96)
                     jjCheckNAddStates(18, 20);
                  break;
               case 6:
                  if (curChar == 96)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0xfffffffeffffffffL & l) != 0L)
                     jjCheckNAddStates(18, 20);
                  break;
               case 8:
                  if (curChar == 96 && kind > 547)
                     kind = 547;
                  break;
               case 12:
                  jjAddStates(15, 17);
                  break;
               case 14:
                  if ((0x100000001000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 16:
                  if ((0x7e0000007eL & l) != 0L)
                     jjAddStates(24, 25);
                  break;
               case 24:
                  if ((0x288000002880L & l) != 0L && kind > 556)
                     kind = 556;
                  break;
               case 28:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(26, 27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 540)
                     kind = 540;
                  jjCheckNAdd(1);
                  break;
               case 1:
                  if (!jjCanMove_3(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 540)
                     kind = 540;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if (!jjCanMove_4(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 541)
                     kind = 541;
                  jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 7:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(18, 20);
                  break;
               case 12:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(15, 17);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 34 - (jjnewStateCnt = startsAt)))
         break;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { break; }
   }
   if (jjmatchedPos > strPos)
      return curPos;

   int toRet = Math.max(curPos, seenUpto);

   if (curPos < toRet)
      for (i = toRet - Math.min(curPos, seenUpto); i-- > 0; )
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) { throw new Error("Internal Error : Please send a bug report."); }

   if (jjmatchedPos < strPos)
   {
      jjmatchedKind = strKind;
      jjmatchedPos = strPos;
   }
   else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
      jjmatchedKind = strKind;

   return toRet;
}
private final int jjStopStringLiteralDfa_18(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            jjmatchedKind = 62;
            jjmatchedPos = 0;
            return -1;
         }
         return -1;
      case 1:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 3:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 4:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 5:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 6:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 7:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 11:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 12:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 13:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 14:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 15:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 16:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 17:
         if ((active0 & 0x800000000000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 62;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_18(int pos, long active0)
{
   return jjMoveNfa_18(jjStopStringLiteralDfa_18(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_18()
{
   switch(curChar)
   {
      case 68:
         return jjMoveStringLiteralDfa1_18(0x800000000000000L);
      case 83:
         return jjStopAtPos(0, 60);
      case 100:
         return jjMoveStringLiteralDfa1_18(0x800000000000000L);
      case 115:
         return jjStopAtPos(0, 60);
      default :
         return jjMoveNfa_18(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_18(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa2_18(active0, 0x800000000000000L);
      case 101:
         return jjMoveStringLiteralDfa2_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(0, active0);
}
private int jjMoveStringLiteralDfa2_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa3_18(active0, 0x800000000000000L);
      case 114:
         return jjMoveStringLiteralDfa3_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(1, active0);
}
private int jjMoveStringLiteralDfa3_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa4_18(active0, 0x800000000000000L);
      case 98:
         return jjMoveStringLiteralDfa4_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(2, active0);
}
private int jjMoveStringLiteralDfa4_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 89:
         return jjMoveStringLiteralDfa5_18(active0, 0x800000000000000L);
      case 121:
         return jjMoveStringLiteralDfa5_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(3, active0);
}
private int jjMoveStringLiteralDfa5_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 68:
         return jjMoveStringLiteralDfa6_18(active0, 0x800000000000000L);
      case 100:
         return jjMoveStringLiteralDfa6_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(4, active0);
}
private int jjMoveStringLiteralDfa6_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa7_18(active0, 0x800000000000000L);
      case 97:
         return jjMoveStringLiteralDfa7_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(5, active0);
}
private int jjMoveStringLiteralDfa7_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 83:
         return jjMoveStringLiteralDfa8_18(active0, 0x800000000000000L);
      case 115:
         return jjMoveStringLiteralDfa8_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(6, active0);
}
private int jjMoveStringLiteralDfa8_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 72:
         return jjMoveStringLiteralDfa9_18(active0, 0x800000000000000L);
      case 104:
         return jjMoveStringLiteralDfa9_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(7, active0);
}
private int jjMoveStringLiteralDfa9_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 80:
         return jjMoveStringLiteralDfa10_18(active0, 0x800000000000000L);
      case 112:
         return jjMoveStringLiteralDfa10_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(8, active0);
}
private int jjMoveStringLiteralDfa10_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa11_18(active0, 0x800000000000000L);
      case 114:
         return jjMoveStringLiteralDfa11_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(9, active0);
}
private int jjMoveStringLiteralDfa11_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 79:
         return jjMoveStringLiteralDfa12_18(active0, 0x800000000000000L);
      case 111:
         return jjMoveStringLiteralDfa12_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(10, active0);
}
private int jjMoveStringLiteralDfa12_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 80:
         return jjMoveStringLiteralDfa13_18(active0, 0x800000000000000L);
      case 112:
         return jjMoveStringLiteralDfa13_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(11, active0);
}
private int jjMoveStringLiteralDfa13_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(11, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(12, active0);
      return 13;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa14_18(active0, 0x800000000000000L);
      case 101:
         return jjMoveStringLiteralDfa14_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(12, active0);
}
private int jjMoveStringLiteralDfa14_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(12, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(13, active0);
      return 14;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa15_18(active0, 0x800000000000000L);
      case 114:
         return jjMoveStringLiteralDfa15_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(13, active0);
}
private int jjMoveStringLiteralDfa15_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(13, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(14, active0);
      return 15;
   }
   switch(curChar)
   {
      case 84:
         return jjMoveStringLiteralDfa16_18(active0, 0x800000000000000L);
      case 116:
         return jjMoveStringLiteralDfa16_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(14, active0);
}
private int jjMoveStringLiteralDfa16_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(14, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(15, active0);
      return 16;
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa17_18(active0, 0x800000000000000L);
      case 105:
         return jjMoveStringLiteralDfa17_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(15, active0);
}
private int jjMoveStringLiteralDfa17_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(15, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(16, active0);
      return 17;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa18_18(active0, 0x800000000000000L);
      case 101:
         return jjMoveStringLiteralDfa18_18(active0, 0x800000000000000L);
      default :
         break;
   }
   return jjStartNfa_18(16, active0);
}
private int jjMoveStringLiteralDfa18_18(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_18(16, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_18(17, active0);
      return 18;
   }
   switch(curChar)
   {
      case 83:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(18, 59);
         break;
      case 115:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(18, 59);
         break;
      default :
         break;
   }
   return jjStartNfa_18(17, active0);
}
private int jjMoveNfa_18(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 62)
                     kind = 62;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 61)
                        kind = 61;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 61)
                     kind = 61;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 62)
                     kind = 62;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfff7fffffff7ffffL & l) != 0L)
                     kind = 62;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 62)
                     kind = 62;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_14(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_14(int pos, long active0)
{
   return jjMoveNfa_14(jjStopStringLiteralDfa_14(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_14()
{
   switch(curChar)
   {
      case 82:
         return jjStopAtPos(0, 47);
      case 114:
         return jjStopAtPos(0, 47);
      default :
         return jjMoveNfa_14(0, 0);
   }
}
private int jjMoveNfa_14(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 49)
                     kind = 49;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 48)
                        kind = 48;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 48)
                     kind = 48;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 49)
                     kind = 49;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffbfffffffbffffL & l) != 0L)
                     kind = 49;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 49)
                     kind = 49;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_9(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_9(int pos, long active0)
{
   return jjMoveNfa_9(jjStopStringLiteralDfa_9(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_9()
{
   switch(curChar)
   {
      case 80:
         return jjStopAtPos(0, 32);
      case 112:
         return jjStopAtPos(0, 32);
      default :
         return jjMoveNfa_9(0, 0);
   }
}
private int jjMoveNfa_9(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 34)
                     kind = 34;
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 33)
                        kind = 33;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 33)
                     kind = 33;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (kind > 34)
                     kind = 34;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffefffffffeffffL & l) != 0L)
                     kind = 34;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 34)
                     kind = 34;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_21()
{
   return jjMoveNfa_21(4, 0);
}
private int jjMoveNfa_21(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(28, 30);
                  else if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 0:
                  if (curChar == 34)
                     jjCheckNAddStates(28, 30);
                  break;
               case 1:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 2:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(28, 30);
                  break;
               case 3:
                  if (curChar == 34 && kind > 549)
                     kind = 549;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 2:
                  jjCheckNAddStates(28, 30);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 2:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(28, 30);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 4 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   0, 1, 3, 1, 2, 4, 5, 19, 20, 22, 23, 24, 25, 26, 28, 11, 
   12, 13, 6, 7, 8, 25, 26, 28, 16, 17, 29, 30, 1, 2, 3, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec4[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec13[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec14[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec15[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec34[i2] & l2) != 0L);
      default :
         if ((jjbitVec5[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_3(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 1:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec39[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec40[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec41[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec42[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec43[i2] & l2) != 0L);
      default :
         if ((jjbitVec5[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_4(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 6:
         return ((jjbitVec46[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec47[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec48[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec49[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec50[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec51[i2] & l2) != 0L);
      default :
         if ((jjbitVec44[i1] & l1) != 0L)
            if ((jjbitVec45[i2] & l2) == 0L)
               return false;
            else
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_BRACKETED_COMMENT",
   "IN_NESTED_BRACKETED_COMMENT",
   "IN_COMMENT",
   "LOOKFOR_DE",
   "LOOKFOR_DER",
   "LOOKFOR_DERB",
   "LOOKFOR_DERBY",
   "LOOKFOR_DERBYDASH",
   "LOOKFOR_DERBYDASHP",
   "LOOKFOR_DERBYDASHPR",
   "LOOKFOR_DERBYDASHPRO",
   "LOOKFOR_DERBYDASHPROP",
   "LOOKFOR_DERBYDASHPROPE",
   "LOOKFOR_DERBYDASHPROPER",
   "LOOKFOR_DERBYDASHPROPERT",
   "LOOKFOR_DERBYDASHPROPERTI",
   "LOOKFOR_DERBYDASHPROPERTIE",
   "LOOKFOR_DERBYDASHPROPERTIES",
   "IT_IS_NOT_DERBYPROPERTIES_COMMENT",
   "PROPERTIES_LIST",
   "IN_DOUBLEQUOTED_IDENTIFIER",
   "IN_DOUBLEQUOTED_STRING",
   "IN_DOUBLEDOLLAR_STRING",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 1, 2, 0, -1, -1, -1, 3, -1, -1, 4, 0, 19, 5, 0, 19, 6, 0, 19, 7, 0, 
   19, 8, 0, 19, 9, 0, 19, 10, 0, 19, 11, 0, 19, 12, 0, 19, 13, 0, 19, 14, 0, 19, 15, 0, 19, 
   16, 0, 19, 17, 0, 19, 18, 0, 19, -1, 20, 0, 19, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 
   0, 23, 0, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1800000000000001L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 
   0xfd69ffffffffL, 
};
static final long[] jjtoSkip = {
   0xe00000000000389eL, 0x0L, 0x0L, 0x0L, 
   0x0L, 0x0L, 0x0L, 0x0L, 
   0x0L, 
};
static final long[] jjtoMore = {
   0x7ffffffffffc760L, 0x0L, 0x0L, 0x0L, 
   0x0L, 0x0L, 0x0L, 0x0L, 
   0x29000000000L, 
};
protected CharStream input_stream;
private final int[] jjrounds = new int[34];
private final int[] jjstateSet = new int[68];
private final StringBuilder jjimage = new StringBuilder();
private StringBuilder image = jjimage;
private int jjimageLen;
private int lengthOfMatch;
protected char curChar;

/** Constructor with parser. */
public SQLGrammarTokenManager(SQLGrammar parserArg, CharStream stream){
   parser = parserArg;
   input_stream = stream;
}

/** Constructor with parser. */
public SQLGrammarTokenManager(SQLGrammar parserArg, CharStream stream, int lexState){
   this(parserArg, stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(CharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 34; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(CharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 24 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   if (jjmatchedPos < 0)
   {
      if (image == null)
         curTokenImage = "";
      else
         curTokenImage = image.toString();
      beginLine = endLine = input_stream.getBeginLine();
      beginColumn = endColumn = input_stream.getBeginColumn();
   }
   else
   {
      String im = jjstrLiteralImages[jjmatchedKind];
      curTokenImage = (im == null) ? input_stream.GetImage() : im;
      beginLine = input_stream.getBeginLine();
      beginColumn = input_stream.getBeginColumn();
      endLine = input_stream.getEndLine();
      endColumn = input_stream.getEndColumn();
   }
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      CommonTokenAction(matchedToken);
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
       case 3:
         jjmatchedKind = 15;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_3();
         break;
       case 4:
         jjmatchedKind = 18;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_4();
         break;
       case 5:
         jjmatchedKind = 21;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_5();
         break;
       case 6:
         jjmatchedKind = 24;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_6();
         break;
       case 7:
         jjmatchedKind = 27;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_7();
         break;
       case 8:
         jjmatchedKind = 30;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_8();
         break;
       case 9:
         jjmatchedKind = 33;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_9();
         break;
       case 10:
         jjmatchedKind = 36;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_10();
         break;
       case 11:
         jjmatchedKind = 39;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_11();
         break;
       case 12:
         jjmatchedKind = 42;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_12();
         break;
       case 13:
         jjmatchedKind = 45;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_13();
         break;
       case 14:
         jjmatchedKind = 48;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_14();
         break;
       case 15:
         jjmatchedKind = 51;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_15();
         break;
       case 16:
         jjmatchedKind = 54;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_16();
         break;
       case 17:
         jjmatchedKind = 57;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_17();
         break;
       case 18:
         jjmatchedKind = 61;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_18();
         break;
       case 19:
         jjmatchedKind = 63;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_19();
         break;
       case 20:
         jjmatchedKind = 64;
         jjmatchedPos = -1;
         curPos = 0;
         curPos = jjMoveStringLiteralDfa0_20();
         break;
       case 21:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_21();
         break;
       case 22:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_22();
         break;
       case 23:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_23();
         if (jjmatchedPos == 0 && jjmatchedKind > 553)
         {
            jjmatchedKind = 553;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           TokenLexicalActions(matchedToken);
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           CommonTokenAction(matchedToken);
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        MoreLexicalActions();
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      case 6 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
           commentNestingDepth = 1;
         break;
      case 8 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
           commentNestingDepth++;
         break;
      case 9 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
      commentNestingDepth--; SwitchTo(commentNestingDepth == 0 ? IN_BRACKETED_COMMENT : IN_NESTED_BRACKETED_COMMENT);
         break;
      case 548 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
         SwitchTo(parser.hasFeature(SQLParserFeature.DOUBLE_QUOTED_STRING) ?
                  IN_DOUBLEQUOTED_STRING : IN_DOUBLEQUOTED_IDENTIFIER);
         break;
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 60 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
          matchedToken.kind = DERBYDASHPROPERTIES;
         break;
      case 538 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    if (parser.hasFeature(SQLParserFeature.INFIX_LOGICAL_OPERATORS))
        matchedToken.kind = OR;
         break;
      case 539 :
        image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    if (parser.hasFeature(SQLParserFeature.INFIX_LOGICAL_OPERATORS))
        matchedToken.kind = AND;
         break;
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}