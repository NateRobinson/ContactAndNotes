package com.gu.baselibrary.utils;

/**
 * Created by ZhuJie on 2015/11/10.
 */
public class ErrorUtils {
    public static String errormessage(int resultcode){
        switch (resultcode){
            case 1011:
                return "手机号已存在";
            case 1012:
                return "手机号不存在";
            case 1021:
                return "传入参数为空";
            case 1022:
                return "校验字符串过期";
            case 1023:
                return "手机号未验证";
            case 1024:
                return "手机号已存在";
            case 1025:
                return "注册失败";
            case 1030:
                return "用户存在";
            case 1031:
                return "用户不存在";
            case 1041:
                return "暂无店铺信息";
            case 1042:
                return "该用户没有宝宝店";
            case 1051:
                return "查询商户详情失败";
            case 1061:
                return "已存在店铺名为的数据";
            case 1101:
                return "没有默认增值税发票";
            case 1111:
                return "用户已拥有一家商户";
            case 1121:
                return "查询失败";
            case 1131:
                return "图片上传失败";
            case 1151:
                return "暂无店员";
            case 1161:
                return "该用户已是您的店员，无法添加";
            case 1162:
                return "该用户已是其他店的店员，无法添加";
            case 1181:
                return "删除失败";
            case 1191:
                return "原密码错误";
            case 1192:
                return "原密码与新密码一致";
            case 1193:
                return "修改失败";
            case 1201:
                return "昵称已存在";
            case 1202:
                return "修改昵称失败";
            case 1211:
                return "修改失败";
            case 1221:
                return "修改性别失败";
            case 1231:
                return "修改城市失败";
            case 1241:
                return "修改个性签名失败";
            case 1251:
                return "原手机号不存在";
            case 1252:
                return "校验字符串已过期";
            case 1253:
                return "新手机号已存在";
            case 1254:
                return "修改失败";
            case 1261:
                return "查询失败";
            case 1271:
                return "此店铺已停用";
            case 1291:
                return "已存在店铺名相同的数据";
            case 1301:
                return "手机号不存在";
            case 1302 :
                return "密码错误";
            case 1303:
                return "用户不是宝宝点用户";
            case 1304:
                return "用户已停用";
            case 1305:
                return "验证码错误";
            case 1306:
                return "字段缺失";
            case 1307:
                return "登录失败";
            case 1321:
                return "校验字符串已过期";
            case 1322:
                return "传入参数手机号与验证手机号不一致";
            case 1323:
                return "新密码与原密码一致";
            case 1331:
                return "更改失败";
            case 1332:
                return "当前用户不是宝宝店主";
            case 1333:
                return "当前用户不存在";
            default:
                return "未知错误";
        }
    }
}
