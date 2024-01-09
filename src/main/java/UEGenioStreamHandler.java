import alexas.NotasIntentHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;


public class UEGenioStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new NotasIntentHandler())
                // TODO colocar o skillID apos criar skill na AmazonDeveloper
                //.withSkillId()
                .build();
    }

    public UEGenioStreamHandler() {
        super(getSkill());
    }
}