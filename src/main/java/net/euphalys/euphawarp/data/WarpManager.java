package net.euphalys.euphawarp.data;

import net.euphalys.api.plugin.IEuphalysPlugin;
import net.euphalys.euphawarp.EuphaWarp;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dinnerwolph
 */
public class WarpManager {

    private final DataSource dataSource;

    public WarpManager(IEuphalysPlugin plugin) {
        this.dataSource = plugin.getDatabaseManager().getDataSource();
    }

    public void registerWarps(String name, Location location) {
        EuphaWarp.warp.put(name, location);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `build_warps`(`name`, `location`) VALUES (?,?)");
            statement.setString(1, name);
            statement.setString(2, location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllWarps() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM build_warps");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String[] locations = resultSet.getString("location").split(";");
                Location location = new Location(Bukkit.getWorld(locations[0]), Double.parseDouble(locations[1]), Double.parseDouble(locations[2]), Double.parseDouble(locations[3]), Float.parseFloat(locations[4]), Float.parseFloat(locations[5]));
                EuphaWarp.warp.put(name, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
