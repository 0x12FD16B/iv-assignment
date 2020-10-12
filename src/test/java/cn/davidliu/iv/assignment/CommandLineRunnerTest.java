package cn.davidliu.iv.assignment;

import org.junit.Test;

public class CommandLineRunnerTest {

    @Test
    public void testCommandLineRunnerTestCanBeInitialized() {
        new CommandLineRunner();
    }

    @Test
    public void testMainMethodCanBeInvoked() {
        CommandLineRunner.main(new String[]{});
    }
}