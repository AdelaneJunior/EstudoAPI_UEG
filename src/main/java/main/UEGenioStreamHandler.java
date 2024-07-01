package main;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import handlers.*;


public class UEGenioStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {

        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new ScheduleIntentHandler(),
                        new HelpIntentHandler(),
                        new FallBackIntentHandler(),
                        new CancelandStopIntentHandler(),
                        new HelloWorldIdentHandler())
                .withSkillId("")
                .build();
    }

    public UEGenioStreamHandler() {
        super(getSkill());
    }
}