package com.example.tasklist.repository.impl;

import com.example.tasklist.model.user.Role;
import com.example.tasklist.model.user.User;
import com.example.tasklist.repository.DataSourceConfig;
import com.example.tasklist.repository.UserRepository;
import com.example.tasklist.repository.mappers.UserRowMapper;
import com.example.tasklist.utils.exception.ResourceMappingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_USER_BY_ID = """
            select u.id as user_id,
                   u.name as user_name,
                   u.username as user_username,
                   u.password as user_password,
                   ur.role as user_role_role,
                   t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            from users u
                left join users_roles ur on u.id = ur.user_id
                left join users_tasks ut on u.id = ut.user_id
                left join tasks t on ut.task_id = t.id
            where u.id = ?
            """;

    private final String FIND_USER_BY_USERNAME = """
            SELECT u.id as user_id,
                   u.name as user_name,
                   u.username as user_username,
                   u.password as user_password,
                   ur.role as user_role_role,
                   t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            FROM users u
                LEFT JOIN users_roles ur ON u.id = ur.user_id
                LEFT JOIN users_tasks ut ON u.id = ut.user_id
                LEFT JOIN tasks t ON ut.task_id = t.id
            WHERE u.username = ?
            """;

    private final String UPDATE = """
            UPDATE users
            SET name = ?
                username = ?
                password = ?
            WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO users (name, username, password)
            VALUES (?, ?, ?)
            """;

    private final String INSERT_USER_ROLE = """
            INSERT INTO users_roles (user_id, role)
            VALUES (?, ?)
            """;

    private final String DELETE = """
            DELETE FROM users
            WHERE id = ?
            """;

    private final String IS_TASK_OWNER = """
            select exists (select 1
                           from users_tasks
                           where user_id = ?
                             and task_id = ?
                           )
            """;

    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();

            PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // позволяет возвращаться в начало ResultSet'а
                    ResultSet.CONCUR_READ_ONLY); // позволяет возвращаться в начало ResultSet'а
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding user by ID");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Connection connection = dataSourceConfig.getConnection();

            PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_USERNAME,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // позволяет возвращаться в начало списка
                    ResultSet.CONCUR_READ_ONLY); //
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while finding user by USERNAME");
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while updating user");
        }
    }

    @Override
    public void create(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                user.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while creating user");
        }
    }

    @Override
    public void insertUserRole(Long userId, Role role) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_USER_ROLE);
            ps.setLong(1, userId);
            ps.setString(2, role.name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while inserting user role");
        }
    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(IS_TASK_OWNER);
            ps.setLong(1, userId);
            ps.setLong(2, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while checking if user is task owner");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Exception while deleting user");
        }
    }

}
