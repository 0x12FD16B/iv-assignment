package cn.davidliu.iv.assignment.support.log;

/**
 * 日志接口实现 (控制台标准输出)
 *
 * @author David Liu
 */
public class StdOutImpl implements Log {

    public StdOutImpl(String clazz) {
        // clazz 为预留参数, 标识日志输出类
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        e.printStackTrace();
    }

    @Override
    public void error(String s) {
        System.err.println(s);
    }

    @Override
    public void info(String s) {
        System.out.println(s);
    }

    @Override
    public void debug(String s) {
        System.out.println(s);
    }

    @Override
    public void warn(String s) {
        System.out.println(s);
    }
}
