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

import io.seata.common.exception.ShouldNeverHappenException;

/**
 * 分支事务的状态
 *
 * @author sharajava
 */
public enum BranchStatus {

    /**
     * 未知.
     * 描述:分布事务状态未知.
     */
    Unknown(0),

    /**
     * 已注册,
     * 描述：已注册到TC。
     */
    Registered(1),

    /**
     * 第一阶段完成.
     * 描述：分支逻辑已在第一阶段成功完成。
     */
    PhaseOne_Done(2),

    /**
     * 第一阶段失败.
     * 描述：分支逻辑在第一阶段失败。
     */
    PhaseOne_Failed(3),

    /**
     * 第一阶段超时.
     * 描述：未报告超时的分支逻辑。
     */
    PhaseOne_Timeout(4),

    /**
     * 第二阶段已落实。
     * 描述：在第二阶段成功完成了提交逻辑。
     */
    PhaseTwo_Committed(5),

    /**
     * 第二阶段提交失败，可重试.
     * 描述：提交逻辑失败但可重试。
     */
    PhaseTwo_CommitFailed_Retryable(6),

    /**
     * 第二阶段提交失败，无法重试.
     * 描述：提交逻辑失败并且不可重试。
     */
    PhaseTwo_CommitFailed_Unretryable(7),

    /**
     * 第二阶段回滚.
     * 描述：回滚逻辑已在第二阶段成功完成。
     */
    PhaseTwo_Rollbacked(8),

    /**
     * 第二阶段回滚失败可重试.
     * 描述：回滚逻辑失败，但可以重试。
     */
    PhaseTwo_RollbackFailed_Retryable(9),

    /**
     * 第二阶段回滚失败，无法重试.
     * 描述：回滚逻辑失败，但不可重试。
     */
    PhaseTwo_RollbackFailed_Unretryable(10);

    private int code;

    BranchStatus(int code) {
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

    private static final Map<Integer, BranchStatus> MAP = new HashMap<>(values().length);

    static {
        for (BranchStatus status : values()) {
            MAP.put(status.getCode(), status);
        }
    }

    /**
     * 获取分支事务状态
     *
     * @param code the code
     * @return 分支事务状态
     */
    public static BranchStatus get(byte code) {
        return get((int) code);
    }

    /**
     * 获取分支事务状态
     *
     * @param code the code
     * @return 分支事务状态
     */
    public static BranchStatus get(int code) {
        BranchStatus status = MAP.get(code);

        if (null == status) {
            throw new ShouldNeverHappenException("Unknown BranchStatus[" + code + "]");
        }

        return status;
    }
}
