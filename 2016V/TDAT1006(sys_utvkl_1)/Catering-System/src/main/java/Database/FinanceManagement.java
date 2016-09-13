package Database;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Evdal on 09.04.2016.
 */
public class FinanceManagement extends Management{
    private final String sqlGetRecipes = "SELECT portions, price, recipe.recipe_id FROM order_recipe, recipe WHERE order_recipe.order_id = ? AND recipe.recipe_id = order_recipe.recipe_id;";
    private final String sqlAddIncome = "INSERT INTO finance VALUES(?,0,CURRENT_DATE);";
    private final String sqlAddOutcome = "INSERT INTO finance VALUES(0,?,CURRENT_DATE)";

    Connection conn = null;
    ResultSet res = null;
    PreparedStatement prep = null;

    /**
     * Constructor for FinanceManagement.
     */
    public FinanceManagement(){
        super();
    }

    private boolean addDoubleToDatabase(String sql, Double input, String errMsg){
        if(setUp()){
            try{
                conn = getConnection();
                conn.setAutoCommit(false);
                prep = conn.prepareStatement(sql);
                prep.setDouble(1, input);
                if(prep.executeUpdate() <= 0)return false;
            }catch (SQLException e){
                System.err.println(errMsg);
                rollbackStatement();
                return false;
            }
            finally {
                finallyStatement(res, prep);
            }
        }
        return true;
    }

    /**
     * Adds income to database.
     * @param income Income.
     * @return True or false if the update was succesfull.
     */
    public boolean addIncomeToDatabase(double income){
        return addDoubleToDatabase(sqlAddIncome, income, "Issue with adding income to database");
    }

    /**
     * Adds outcome to database.
     * @param outcome Outcome.
     * @return True or false if the update was successfull.
     */
    public boolean addOutcomeToDatabase(double outcome){
        return addDoubleToDatabase(sqlAddOutcome, outcome, "Issue with adding outcome to database");
    }

    /**
     * Method for getting the information on a specific recipe from a order.
     * @param id Id of the order.
     * @return List of recipes in a specific order.
     */
    public ArrayList<Object[]> getOrderRecipeInfo(int id){
        ArrayList<Object[]> out = new ArrayList<>();
        if(setUp()){
            try{
                conn = getConnection();
                prep = conn.prepareStatement(sqlGetRecipes);
                prep.setInt(1, id);
                res = prep.executeQuery();

                while (res.next()){
                    Object[] obj = new Object[2];
                    obj[0] = res.getInt("portions");
                    obj[1] = res.getDouble("price");
                    out.add(obj);
                }
            }catch (SQLException e){
                System.err.println("Issue with getting recipe information.");
            }
            finally {
                finallyStatement(res, prep);
            }
        }
        return out;
    }
}
