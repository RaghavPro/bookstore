package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class CategoryService extends Service<Category> {
    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public CategoryService(Connection conn) {
        super(conn);
    }

    @Override
    public List<Category> get(Object[] args, String query, Object... queryParams) {
        long parentCategoryId = (long) args[0];
        query = "SELECT * FROM category WHERE parent " + (parentCategoryId == 0 ? "is NULL" : " = ?") + " or categoryid = ?";
        if (parentCategoryId != 0) {
            queryParams = new Object[]{
                    parentCategoryId, parentCategoryId
            };
        } else {
            queryParams = new Object[]{
                    parentCategoryId
            };
        }
        List<Category> categories = null;
        ResultSet rs = null;
        try {
            /* Executes the query */
            rs = execute(query, queryParams);
            if (rs == null)
                return null; //null
            categories = new ArrayList<>();
            while (rs.next()) {
                long categoryId = rs.getLong("categoryid");
                long parent = rs.getLong("parent");
                String categoryName = rs.getString("categoryName");

                Category category = new Category();

                category.setCategoryId(categoryId);
                category.setParentId(parent);
                category.setCategoryName(categoryName);

                categories.add(category);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    @Override
    public boolean dataManipulation(String query, Object... queryParams) {
        return false;
    }
}
