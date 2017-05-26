package com.example.sara.qafelah;

/**
 * Created by SARA on 3 ماي، 2017 م.
 */

public class EncouragingWords {
    final public String[] words4EachAnswer = {"بارك الله فيك","نفع الله بك" ,"زادك الله رفعة" , "أحسنت" , "أجزل الله لك الأجر" , "أحسن الله إليك" };
    final public String[] wordsAfterLevel = {"أحسنت!" , "اجتزت المرحلة بنجاح، تابع السير!" , "رائع، لقد تخطيت المرحلة بنجاح!"} ; // فكر فكر

    public int words4EachAnswerLength(){
        return words4EachAnswer.length ;
    }
    public int wordsAfterLevelLength(){
        return wordsAfterLevel.length ;
    }

}
