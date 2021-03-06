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
package com.taobao.gecko.service.impl;

import java.util.Map;
import java.util.Set;


/**
 * 
 * 分组连接管理器的MBean
 * 
 * @author boyan
 * 
 * @since 1.0, 2010-1-13 下午01:48:50
 */

public interface GroupManagerMBean {
    /**
     * 获取分组对应的连接信息映射
     * 
     * @return
     */
    Map<String, Set<String>> getGroupConnectionInfo();
}
