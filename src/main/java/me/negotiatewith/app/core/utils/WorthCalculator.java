package me.negotiatewith.app.core.utils;

import java.util.Arrays;
import java.util.List;

public class WorthCalculator {

    public static double getWorthOfSkills(List<String> userSkills, int ctc) {

        double costPerRelevantSkill = (ctc * 0.80) / TrainingSet.SKILLS_RELEVANT_ANDROID.length;
        double costPerEssentialSkill = (ctc * 0.20) / TrainingSet.SKILLS_ESSENTIAL_ANDROID.length;

        return (getNumberOfEssentialSkills(userSkills, Arrays.asList(TrainingSet.SKILLS_ESSENTIAL_ANDROID)) * costPerEssentialSkill)
                + (getNumberOfRelevantSkills(userSkills, Arrays.asList(TrainingSet.SKILLS_RELEVANT_ANDROID)) * costPerRelevantSkill);
    }

    private static int getNumberOfRelevantSkills(List<String> usersSkills, List<String> trainerSet) {

        int number = 0;
        for (String skill : usersSkills) {
            if (containsCaseInsensitive(skill, trainerSet))
                number++;
        }
        System.out.println("Relevant Matches = " + number);
        return number;
    }

    private static int getNumberOfEssentialSkills(List<String> usersSkills, List<String> trainerSet) {

        int number = 0;
        for (String skill : usersSkills) {
            if (containsCaseInsensitive(skill.toLowerCase(), trainerSet))
                number++;
        }
        System.out.println("Essential Matches = " + number);
        return number;
    }

    public static boolean containsCaseInsensitive(String s, List<String> l){
        for (String string : l){
            if (string.toLowerCase().contains(s.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
