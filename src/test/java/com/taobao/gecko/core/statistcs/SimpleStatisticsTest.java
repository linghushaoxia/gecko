/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taobao.gecko.core.statistcs;

import junit.framework.TestCase;

import com.taobao.gecko.core.statistics.Statistics;
import com.taobao.gecko.core.statistics.impl.SimpleStatistics;


public class SimpleStatisticsTest extends TestCase {

    Statistics statistics;


    @Override
    protected void setUp() throws Exception {
        statistics = new SimpleStatistics();
        statistics.start();
    }


    @Override
    protected void tearDown() throws Exception {
        statistics.stop();
        assertFalse(statistics.isStatistics());
    }


    public void testRead() throws Exception {
        assertTrue(statistics.isStatistics());
        assertEquals(0, statistics.getRecvMessageCount());
        assertEquals(0.0, statistics.getRecvMessageCountPerSecond());
        assertEquals(0, statistics.getRecvMessageTotalSize());
        assertEquals(0, statistics.getRecvMessageAverageSize());
        statistics.statisticsRead(4096);
        assertEquals(1, statistics.getRecvMessageCount());
        assertEquals(4096, statistics.getRecvMessageTotalSize());
        assertEquals(4096, statistics.getRecvMessageAverageSize());

        statistics.statisticsRead(1024);
        assertEquals(2, statistics.getRecvMessageCount());
        assertEquals(5120, statistics.getRecvMessageTotalSize());
        assertEquals(2560, statistics.getRecvMessageAverageSize());
        Thread.sleep(1000);
        assertEquals(2.0, statistics.getRecvMessageCountPerSecond(), 0.1);

        statistics.statisticsRead(512);
        assertEquals(3, statistics.getRecvMessageCount());
        assertEquals(5632, statistics.getRecvMessageTotalSize());
        assertEquals(1877, statistics.getRecvMessageAverageSize());
        assertEquals(3.0, statistics.getRecvMessageCountPerSecond(), 0.1);
        statistics.statisticsRead(0);
        assertEquals(3, statistics.getRecvMessageCount());
        assertEquals(5632, statistics.getRecvMessageTotalSize());
        assertEquals(1877, statistics.getRecvMessageAverageSize());
        assertEquals(3.0, statistics.getRecvMessageCountPerSecond(), 0.1);

        statistics.statisticsRead(-100);
        assertEquals(3, statistics.getRecvMessageCount());
        assertEquals(5632, statistics.getRecvMessageTotalSize());
        assertEquals(1877, statistics.getRecvMessageAverageSize());
        assertEquals(3.0, statistics.getRecvMessageCountPerSecond(), 0.1);

        statistics.restart();
        assertEquals(0, statistics.getRecvMessageCount());
        assertEquals(0.0, statistics.getRecvMessageCountPerSecond());
        assertEquals(0, statistics.getRecvMessageTotalSize());
        assertEquals(0, statistics.getRecvMessageAverageSize());
    }


    public void testWrite() throws Exception {
        assertEquals(0, statistics.getWriteMessageCount());
        assertEquals(0.0, statistics.getWriteMessageCountPerSecond());
        assertEquals(0, statistics.getWriteMessageTotalSize());
        assertEquals(0, statistics.getWriteMessageAverageSize());
        statistics.statisticsWrite(4096);
        assertEquals(1, statistics.getWriteMessageCount());
        assertEquals(4096, statistics.getWriteMessageTotalSize());
        assertEquals(4096, statistics.getWriteMessageAverageSize());

        statistics.statisticsWrite(1024);
        assertEquals(2, statistics.getWriteMessageCount());
        assertEquals(5120, statistics.getWriteMessageTotalSize());
        assertEquals(2560, statistics.getWriteMessageAverageSize());
        Thread.sleep(1000);
        assertEquals(2.0, statistics.getWriteMessageCountPerSecond(), 0.1);

        statistics.statisticsWrite(512);
        assertEquals(3, statistics.getWriteMessageCount());
        assertEquals(5632, statistics.getWriteMessageTotalSize());
        assertEquals(1877, statistics.getWriteMessageAverageSize());
        assertEquals(3.0, statistics.getWriteMessageCountPerSecond(), 0.1);

        statistics.statisticsWrite(0);
        assertEquals(3, statistics.getWriteMessageCount());
        assertEquals(5632, statistics.getWriteMessageTotalSize());
        assertEquals(1877, statistics.getWriteMessageAverageSize());
        assertEquals(3.0, statistics.getWriteMessageCountPerSecond(), 0.1);
        statistics.statisticsWrite(-1);
        assertEquals(3, statistics.getWriteMessageCount());
        assertEquals(5632, statistics.getWriteMessageTotalSize());
        assertEquals(1877, statistics.getWriteMessageAverageSize());
        assertEquals(3.0, statistics.getWriteMessageCountPerSecond(), 0.1);
    }


    public void testProcess() {

        assertEquals(0.0, statistics.getProcessedMessageAverageTime());
        assertEquals(0, statistics.getProcessedMessageCount());
        statistics.statisticsProcess(1500);
        assertEquals(1500.0, statistics.getProcessedMessageAverageTime(), 0.1);
        assertEquals(1, statistics.getProcessedMessageCount());

        statistics.statisticsProcess(0);
        assertEquals(750.0, statistics.getProcessedMessageAverageTime(), 0.1);
        assertEquals(2, statistics.getProcessedMessageCount());

        statistics.statisticsProcess(987);
        assertEquals(829.0, statistics.getProcessedMessageAverageTime(), 0.1);
        assertEquals(3, statistics.getProcessedMessageCount());

        statistics.statisticsProcess(-100);
        assertEquals(829.0, statistics.getProcessedMessageAverageTime(), 0.1);
        assertEquals(3, statistics.getProcessedMessageCount());
    }

}
