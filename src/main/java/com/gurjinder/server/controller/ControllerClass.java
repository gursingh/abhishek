package com.gurjinder.server.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gurjinder.server.beans.IpBean;
import com.gurjinder.server.beans.LoginResponseBean;
import com.gurjinder.server.dao.DeviceProfileRepository;
import com.gurjinder.server.dao.SalesManProfileRepository;
import com.gurjinder.server.dao.UserRepository;
import com.gurjinder.server.dao.entity.DeviceProfile;
import com.gurjinder.server.dao.entity.SalesManProfile;
import com.gurjinder.server.dao.entity.User;

@RestController
public class ControllerClass {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DeviceProfileRepository deviceProfileRepository;
	@Autowired
	private SalesManProfileRepository salesManProfileRepository;

	@RequestMapping(value = "/ip", method = RequestMethod.PUT)
	public String updateIP(@RequestParam("token") String token,
			@RequestBody IpBean ipBean, HttpServletResponse httpServletResponse) {
		User user = userRepository.findByToken(token);
		if (user != null) {
			user.setIpAdddres(ipBean.getIp());
			user.setPort(ipBean.getPort());
			user.setIpUpdationTime(new Date());
			userRepository.save(user);
			return "success";
		}
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return "unauthorize";
	}

	@RequestMapping(value = "/ip", method = RequestMethod.GET)
	public IpBean getIP(@RequestParam("token") String token,
			@RequestParam("storename") String storename,
			HttpServletResponse httpServletResponse) {
		User user = userRepository.findByToken(token);
		if (user != null) {
			if (user.getRole().equals("ROLE_SALESMAN")) {
				SalesManProfile salesManProfile = salesManProfileRepository
						.findByUsername(user.getUsername());
				DeviceProfile deviceProfile = deviceProfileRepository
						.findByStoreName(salesManProfile.getStoreName());
				if (deviceProfile != null) {
					user = userRepository.findByUsername(deviceProfile
							.getUsername());
					if (user != null) {
						IpBean ipBean = new IpBean();
						ipBean.setIp(user.getIpAdddres());
						ipBean.setPort(user.getPort());
						return ipBean;
					}
				}
			}

		}
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return null;
	}

	public static void main(String a[]) {
		System.out.println(new MessageDigestPasswordEncoder("MD5")
				.encodePassword("password", "salesman"));
	}

	@Autowired
	private org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public LoginResponseBean updateIP(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletResponse httpServletResponse) {
		String userNamePaswdEncode = passwordEncoder.encodePassword(password,
				username);
		User user = userRepository.findByUsername(username);
		if (user != null) {
			if (user.getPassword().equals(userNamePaswdEncode)) {
				String token = passwordEncoder.encodePassword(
						new ObjectId().toString(), username);
				user.setToken(token);
				userRepository.save(user);
				String storeName = "";
				if (user.getRole().equals("ROLE_DEVICE")) {
					storeName = deviceProfileRepository
							.findByUsername(username).getStoreName();
				} else if (user.getRole().equals("ROLE_SALESMAN")) {
					storeName = salesManProfileRepository.findByUsername(
							username).getStoreName();
				}
				LoginResponseBean loginResponseBean = new LoginResponseBean();
				loginResponseBean.setRole(user.getRole());
				loginResponseBean.setStoreName(storeName);
				loginResponseBean.setToken(token);
				return loginResponseBean;
			}

		}
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return null;
	}
}
