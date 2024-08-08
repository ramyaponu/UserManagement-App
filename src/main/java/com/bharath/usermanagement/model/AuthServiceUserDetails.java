package com.bharath.usermanagement.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bharath.usermanagement.entity.UserDetail;




public class AuthServiceUserDetails implements UserDetails{

	
		private String username;
		private String password;

		public AuthServiceUserDetails(UserDetail userDetails) {
			super();
			if (userDetails.getEmail() != null)
				this.username = userDetails.getEmail();
			else if (userDetails.getPhoneNumber() != null)
				this.username = userDetails.getPhoneNumber();
			this.password = userDetails.getPassword();
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return null;
		}

		@Override
		public String getPassword() {

			return password;
		}

		@Override
		public String getUsername() {

			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
}
