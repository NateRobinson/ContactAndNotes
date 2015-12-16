package com.nate.contactandnotes.db;

import com.nate.contactandnotes.model.PhoneContactModel;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * Created by Nate on 2015/11/20.
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
    public static List<PhoneContactModel> queryAllPhoneContactModel() throws DbException {
        return getInstance().selector(PhoneContactModel.class).findAll();
    }

    /**
     * 当查询发现还没有手机联系人导入过的时候，就执行这一步，插入手机联系人到手机的PhoneContactModel表中
     */
    public static void insertPhoneContactModels(List<PhoneContactModel> phoneContactModels) throws DbException {
        if (phoneContactModels == null || phoneContactModels.size() == 0) {
            throw new IllegalArgumentException("PhoneContactModel list can not be empty or null");
        }
        for (PhoneContactModel model : phoneContactModels) {
            getInstance().save(model);
        }
    }
}
