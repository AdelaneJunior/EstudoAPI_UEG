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
                        new GradeIntentHandler(),
                        new DetailedGradeIntentHandler(),
                        new ScheduleIntentHandler(),
                        new SingUpIntentHandler(),
                        new HelpIntentHandler(),
                        new FallBackIntentHandler(),
                        new HelloWorldIdentHandler())
                .withSkillId("amzn1.ask.skill.073dca99-2bcb-4174-a6c7-df1d41604c8e")
                .build();
    }

    public UEGenioStreamHandler() {
        super(getSkill());
    }
}