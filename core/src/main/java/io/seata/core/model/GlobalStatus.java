/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局事务状态
 *
 * @author sharajava
 */
public enum GlobalStatus {

    /**
     * 位置状态
     */
    // Unknown
    UnKnown(0),

    /**
     * 开始。
     */
    // PHASE 1: can accept new branch registering.
    Begin(1),

    /**
     * 正在提交运行状态
     */
    // Committing.
    Committing(2),

    /**
     * 提交重试运行状态
     */
    // Retrying commit after a recoverable failure.
    CommitRetrying(3),

    /**
     * 回滚运行状态。
     */
    // Rollbacking
    Rollbacking(4),

    /**
     * 回滚重试运行状态
     */
    // Retrying rollback after a recoverable failure.
    RollbackRetrying(5),

    /**
     * 回滚运行状态(TC超时导致)
     */
    // Rollbacking since timeout
    TimeoutRollbacking(6),

    /**
     * 回滚重试运行状态(TC超时导致)
     */
    // Retrying rollback (since timeout) after a recoverable failure.
    TimeoutRollbackRetrying(7),

    /**
     * 所有分支都可以异步提交。提交尚未完成，但是可以将其视为已提交给TM / RM客户端。
     */
    AsyncCommitting(8),

    /**
     * 提交完成状态
     */
    // Finally: global transaction is successfully committed.
    Committed(9),

    /**
     * 提交失败状态（多次重试后被中止）。
     */
    // Finally: failed to commit
    CommitFailed(10),

    /**
     * 回滚完成状态。
     */
    // Finally: global transaction is successfully rollbacked.
    Rollbacked(11),

    /**
     * 回滚失败。
     */
    // Finally: failed to rollback
    RollbackFailed(12),

    /**
     * 超时回滚。
     */
    // Finally: global transaction is successfully rollbacked since timeout.
    TimeoutRollbacked(13),

    /**
     * 超时回滚失败。
     */
    // Finally: failed to rollback since timeout
    TimeoutRollbackFailed(14),

    /**
     * 已完成。
     */
    // Not managed in session MAP any more
    Finished(15);

    private int code;

    GlobalStatus(int code) {
        this.code = code;
    }

    /**
     * 获取code
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }



    private static final Map<Integer, GlobalStatus> MAP = new HashMap<>(values().length);

    static {
        for (GlobalStatus status : values()) {
            MAP.put(status.code, status);
        }
    }

    /**
     * 获取全局事务状态
     *
     * @param code the code
     * @return the global status
     */
    public static GlobalStatus get(byte code) {
        return get((int)code);
    }

    /**
     * 获取全局事务状态
     *
     * @param code the code
     * @return the global status
     */
    public static GlobalStatus get(int code) {
        GlobalStatus status = MAP.get(code);

        if (null == status) {
            throw new IllegalArgumentException("Unknown GlobalStatus[" + code + "]");
        }

        return status;
    }
}
