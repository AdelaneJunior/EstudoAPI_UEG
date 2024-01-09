package main;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import handlers.GradeIntentHandler;
import handlers.HelloWorldIdentHandler;
import handlers.LaunchRequestHandler;


public class UEGenioStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new GradeIntentHandler(),
                        new HelloWorldIdentHandler(),
                        new LaunchRequestHandler())
                .withSkillId("amzn1.ask.skill.073dca99-2bcb-4174-a6c7-df1d41604c8e")
                .build();
    }

    public UEGenioStreamHandler() {
        super(getSkill());
    }
}