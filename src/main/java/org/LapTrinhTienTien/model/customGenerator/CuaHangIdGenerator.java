package org.LapTrinhTienTien.model.customGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CuaHangIdGenerator implements IdentifierGenerator {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "HD";
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(CAST(REPLACE(ma_hd, 'HD', '') AS UNSIGNED)) AS max_id FROM hoa_don");

            if (rs.next()) {
                int maxId = rs.getInt("max_id") + 1;
                String generatedId = prefix + String.format("%03d", maxId);
                return generatedId;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
