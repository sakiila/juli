package me.bob.juli.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.bob.juli.enums.ResultStatus;

/**
 * @author baobo
 * @date 2022/1/11 17:24
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BasicResult<T> {
    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public BasicResult(ResultStatus status) {
        this(status, null);
    }

    public BasicResult(ResultStatus status, T data) {
        this(status, status.getMsg(), data);
    }

    public BasicResult(ResultStatus status, String msg, T data) {
        this.code = status.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * @return 默认成功响应
     */
    public static BasicResult<Void> success() {
        return new BasicResult<>(ResultStatus.SUCCESS);
    }

    /**
     * 自定义信息的成功响应
     * <p>通常用作插入成功等并显示具体操作通知如: return BasicResult.success("发送信息成功")</p>
     *
     * @param msg 信息
     * @return 自定义信息的成功响应
     */
    public static <T> BasicResult<T> success(String msg) {
        return new BasicResult<>(ResultStatus.SUCCESS, msg, null);
    }

    /**
     * 带数据的成功响应
     *
     * @param data 数据
     * @return 带数据的成功响应
     */
    public static <T> BasicResult<T> success(T data) {
        return new BasicResult<>(ResultStatus.SUCCESS, data);
    }

    /**
     * @return 默认失败响应
     */
    public static <T> BasicResult<T> fail() {
        return new BasicResult<>(
                ResultStatus.FAIL,
                ResultStatus.FAIL.getMsg(),
                null
        );
    }

    /**
     * 自定义错误信息的失败响应
     *
     * @param msg 错误信息
     * @return 自定义错误信息的失败响应
     */
    public static <T> BasicResult<T> fail(String msg) {
        return fail(ResultStatus.FAIL, msg);
    }

    /**
     * 自定义状态的失败响应
     *
     * @param status 状态
     * @return 自定义状态的失败响应
     */
    public static <T> BasicResult<T> fail(ResultStatus status) {
        return fail(status, status.getMsg());
    }

    /**
     * 自定义状态和信息的失败响应
     *
     * @param status 状态
     * @param msg    信息
     * @return 自定义状态和信息的失败响应
     */
    public static <T> BasicResult<T> fail(ResultStatus status, String msg) {
        return new BasicResult<>(status, msg, null);
    }

}
