package widget;

import dataModle.JWTHeader;
import dataModle.JWTPayLoad;

//生成token的工具类，采用建造者模式
public class TokenUnits {
    public static class Builder{
        private TokenParams tokenParams;
        public Builder(){
            tokenParams=new TokenParams();
        }
        public Builder setIat(String iat){
            tokenParams.mIat=iat;
            return this;
        }
        public Builder setExp(String exp){
            tokenParams.mExp=exp;
            return this;
        }
        public Builder setUid(String uid){
            tokenParams.mUid=uid;
            return this;
        }
        public Builder setTargetUser(String targetUser){
            tokenParams.mTargetUser=targetUser;
            return this;
        }

        public String createToken(){
            tokenParams.mJwtPayLoad=new JWTPayLoad(tokenParams.mIat,tokenParams.mExp
                    ,tokenParams.mUid,tokenParams.mTargetUser);
            String jwtHeaderStr=CommonUnits.getStrFromObjByBase64(tokenParams.mJwtHeader);
            String jwtPayLoadStr=CommonUnits.getStrFromObjByBase64(tokenParams.mJwtPayLoad);
            String result=CommonUnits.getStrByAlg_HS256(jwtHeaderStr+"."+jwtPayLoadStr,tokenParams.mSecert);
            String token=jwtHeaderStr+"."+jwtPayLoadStr+"."+result;
            return token;
        }
    }
    private static class TokenParams{
        private JWTHeader mJwtHeader=new JWTHeader();
        private String mIat;//jwt的签发时间
        private String mExp;//过期时间
        private String mUid;//用户id
        private String mTargetUser;//用户账号
        private  JWTPayLoad mJwtPayLoad;
        private  final String mSecert=Constants.JWT_SECERT;//jwt密钥
    }
}
