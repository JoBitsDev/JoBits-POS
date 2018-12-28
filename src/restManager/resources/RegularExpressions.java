/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.resources;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class RegularExpressions {

    public static final String ONLY_WORDS_SEPARATED_WITH_SPACES = "[A-Z][[a-zA-Z]* X*[\\s]]*";
    
    public static final String MOBILE_PHONE_NUMBER = "[[53][0-9]*]*";
    
    public static final String HOME_PHONE_NUMBER = "[[0-9]{6,8}]{0,1}";
    
    public static final String USER_NAME = "[a-z]{3,20}";
    
}
