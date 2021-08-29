package cn.cdcyy.partybuilding.management.config;

import cn.cdcyy.partybuilding.common.CommonResponse;
import cn.cdcyy.partybuilding.common.CommonResponseEnum;
import cn.cdcyy.partybuilding.dao.entity.UserEntity;
import cn.cdcyy.partybuilding.dao.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public MyWebSecurityConfigurerAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userName -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);
            Optional<UserEntity> userEntityOptional = userRepository.findOne(Example.of(userEntity));
            if (!userEntityOptional.isPresent()) {
                throw new UsernameNotFoundException("用户不存在");
            }
            UserEntity entity = userEntityOptional.get();
            return new User(entity.getUserName(), entity.getPassword(), new ArrayList<>());
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(CommonResponse.success(authentication)));
                    writer.flush();
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(CommonResponse.fail(
                            CommonResponseEnum.UNAUTHORIZED.getCode(), CommonResponseEnum.UNAUTHORIZED.getMessage())));
                    writer.flush();
                })
                .and().logout().permitAll()
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(CommonResponse.success("success")));
                    writer.flush();
                })
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(CommonResponse.fail(
                            CommonResponseEnum.UNAUTHORIZED.getCode(), CommonResponseEnum.UNAUTHORIZED.getMessage())));
                    writer.flush();
                })
                // 关闭csrf，token模式不存在csrf工具
                .and().csrf().disable();
    }
}
