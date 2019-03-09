package me.philcali.aoc.day4;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

import com.google.auto.service.AutoService;

import me.philcali.aoc.common.DailyEvent;
import me.philcali.aoc.common.DailyInputEvent;

@AutoService(DailyEvent.class)
public class ProblemOne implements DailyInputEvent {
    private final Function<List<String>, Map<String, List<GuardSleepEvent>>> sleepActivity;

    public ProblemOne(final Function<List<String>, Map<String, List<GuardSleepEvent>>> sleepActivity) {
        this.sleepActivity = sleepActivity;
    }

    public ProblemOne() {
        this(new GuardToSleepActivityFunction());
    }

    @Override
    public int day() {
        return 4;
    }

    @Override
    public int problem() {
        return 1;
    }

    @Override
    public int year() {
        return 2018;
    }

    @Override
    public void run() {
        long longestSleep = 0;
        SelectedGuardEvent chosenGuard = null;
        final PriorityQueue<SelectedGuardEvent> queue = sleepActivity.andThen(new SleepActivityToQueue()).apply(readLines());
        while (!queue.isEmpty()) {
            final SelectedGuardEvent event = queue.poll();
            if (event.totalTimeAsleep() > longestSleep) {
                chosenGuard = event;
                longestSleep = chosenGuard.totalTimeAsleep();
            }
        }
        System.out.println("Guard " + chosenGuard.guardId() + " slept the longest with " + longestSleep + " minutes");
        System.out.println("Guard " + chosenGuard.guardId() + " slept the most on minute " + chosenGuard.minuteSleptTheMost());
        System.out.println("Therefore the value is guardId * most minute: " + Integer.parseInt(chosenGuard.guardId()) * chosenGuard.minuteSleptTheMost());
    }
}
