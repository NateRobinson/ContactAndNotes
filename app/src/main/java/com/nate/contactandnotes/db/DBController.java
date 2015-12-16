package com.nate.contactandnotes.db;

import com.nate.contactandnotes.model.ContactModel;
import com.nate.contactandnotes.model.PhoneContactModel;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * Created by Nate on 2015/11/20.数据库操作管理类
 */
public class DBController {

    private static final String DB_NAME = "cn.db";
    private static DbManager dbManager = null;

    private DBController() {

    }

    /**
     * 单列模式的controller
     *
     * @return
     */
    public static DbManager getInstance() {
        if (dbManager == null) {
            DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                    .setDbName(DB_NAME)
                    //.setDbDir(new File("/sdcard"))
                    .setDbVersion(1)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: ...
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                        }
                    });
            dbManager = x.getDb(daoConfig);
        }
        return dbManager;
    }

    /**
     * 查询PhoneContactModel的数据
     */
    public static List<PhoneContactModel> selectAllPhoneContactModel() {
        List<PhoneContactModel> list = null;
        try {
            list = getInstance().selector(PhoneContactModel.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 当查询发现还没有手机联系人导入过的时候，就执行这一步，插入手机联系人到手机的PhoneContactModel表中
     */
    public static void insertPhoneContactModels(List<PhoneContactModel> phoneContactModels) {
        if (phoneContactModels == null || phoneContactModels.size() == 0) {
            throw new IllegalArgumentException("PhoneContactModel list can not be empty or null");
        }
        try {
            for (PhoneContactModel model : phoneContactModels) {
                getInstance().save(model);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存ContactModel或ContactModel的List到数据库,
     * 如果该类型的id是自动生成的, 则保存完后会给id赋值.
     *
     * @param contactModel
     * @throws DbException
     */
    public static boolean insertContactModel(ContactModel contactModel) {
        boolean flag = false;
        try {
            flag = getInstance().saveBindingId(contactModel);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 当添加了一个phonecontactmodel到contactmodel之后，需要对应的将之前的phonecontactmodel状态改为已添加
     *
     * @param phoneContactModel
     */
    public static void updatePhoneContactModel(PhoneContactModel phoneContactModel) {
        try {
            getInstance().update(phoneContactModel, WhereBuilder.b("id", "=", phoneContactModel.getId()), "isadded");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查出所有的ContactModel对象
     *
     * @return
     */
    public static List<ContactModel> selectAllContactModel() {
        List<ContactModel> list = null;
        try {
            list = getInstance().findAll(ContactModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

}
