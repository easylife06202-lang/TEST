/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bo;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;

public class BaseLandRegionalFactorFileBo {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String process_import(String string, String string2, boolean bl, Connection connection) throws IOException {
        Object object;
        File file;
        if (StringProcess.isEmpty(string2)) {
            return "\u5c1a\u672a\u8a2d\u5b9a\u4efb\u4f55\u6a94\u6848!!";
        }
        try {
            file = new File(string + File.separator + string2);
            if (!file.exists()) {
                return "\u627e\u4e0d\u5230\u6a94\u6848!!";
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return "\u627e\u4e0d\u5230\u6a94\u6848!!";
        }
        ObjectInputStream objectInputStream = null;
        try {
            Object object2;
            int n;
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            int n2 = 14;
            object = new byte[n2];
            for (int i = 0; i < n2; ++i) {
                object[i] = objectInputStream.readByte();
            }
            String string3 = new String((byte[])object);
            if (!string3.equals(string2)) {
                String string4 = "\u6a94\u540d\u4e0d\u4e00\u81f4!!\uff0c\u7121\u6cd5\u532f\u5165\u6a94\u6848!!";
                return string4;
            }
            ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList = null;
            ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList2 = null;
            int n3 = objectInputStream.readInt();
            arrayList = new ArrayList<NVO_BASELAND_REGIONAL_FACTOR>(n3);
            for (n = 0; n < n3; ++n) {
                NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)objectInputStream.readObject();
                arrayList.add(nVO_BASELAND_REGIONAL_FACTOR);
            }
            n = objectInputStream.readInt();
            arrayList2 = new ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD>(n);
            for (int i = 0; i < n; ++i) {
                object2 = (NVO_BASELAND_REGIONAL_FACTOR_STD)objectInputStream.readObject();
                arrayList2.add((NVO_BASELAND_REGIONAL_FACTOR_STD)object2);
            }
            NDAO_BASELAND_REGIONAL_FACTOR nDAO_BASELAND_REGIONAL_FACTOR = new NDAO_BASELAND_REGIONAL_FACTOR();
            object2 = new NDAO_BASELAND_REGIONAL_FACTOR_STD();
            if (!bl) {
                for (NVO_BASELAND_REGIONAL_FACTOR cloneable22 : arrayList) {
                    if (!nDAO_BASELAND_REGIONAL_FACTOR.isExist(cloneable22, connection)) continue;
                    String string4 = "hasData";
                    return string4;
                }
                for (NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD : arrayList2) {
                    if (!((DaoBase)object2).isExist(nVO_BASELAND_REGIONAL_FACTOR_STD, connection)) continue;
                    String string5 = "hasData";
                    return string5;
                }
            }
            connection.setAutoCommit(false);
            Iterator iterator = new HashMap();
            for (NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR : arrayList) {
                String string6 = nVO_BASELAND_REGIONAL_FACTOR.getCity() + "_" + nVO_BASELAND_REGIONAL_FACTOR.getYear() + "_" + nVO_BASELAND_REGIONAL_FACTOR.getDist() + "_" + nVO_BASELAND_REGIONAL_FACTOR.getVersion() + "_" + StringProcess.NULL(nVO_BASELAND_REGIONAL_FACTOR.getBaseno());
                if (((HashMap)((Object)iterator)).containsKey(string6)) continue;
                ((HashMap)((Object)iterator)).put(string6, nVO_BASELAND_REGIONAL_FACTOR);
            }
            ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList3 = new ArrayList<NVO_BASELAND_REGIONAL_FACTOR>();
            ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList4 = new ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD>();
            for (Map.Entry entry : ((HashMap)((Object)iterator)).entrySet()) {
                NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)entry.getValue();
                arrayList3.addAll(nDAO_BASELAND_REGIONAL_FACTOR.queryDeleteForCopy(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), connection));
                arrayList4.addAll(((NDAO_BASELAND_REGIONAL_FACTOR_STD)object2).queryDeleteForCopyStd(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), connection));
            }
            if (arrayList3 != null && arrayList3.size() > 0) {
                nDAO_BASELAND_REGIONAL_FACTOR.delete(arrayList3, connection);
            }
            if (arrayList4 != null && arrayList4.size() > 0) {
                ((DaoBase)object2).delete(arrayList4, connection);
            }
            nDAO_BASELAND_REGIONAL_FACTOR.create(arrayList, connection);
            ((DaoBase)object2).create(arrayList2, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            exception.printStackTrace();
            object = "\u767c\u751f\u932f\u8aa4\uff0c\u532f\u5165\u5931\u6557!! " + exception.getMessage();
            return object;
        }
        finally {
            SqlUtil.close(connection);
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return "sucess";
    }
}

