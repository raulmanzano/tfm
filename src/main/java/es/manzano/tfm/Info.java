package es.manzano.tfm;

public class Info {
	private String sessionId;
	private String userId;
	private String previous;
	private String current;
	private String tokenPrevious;
	private String tokenCurrent;
	
	
	public Info(String sessionId, String userId, String previous, String current, String tokenPrevious, String tokenCurrent) {
		super();
		this.sessionId = sessionId;
		this.userId = userId;
		this.previous = previous;
		this.current = current;
		this.tokenPrevious = tokenPrevious;
		this.tokenCurrent = tokenCurrent;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String Previous) {
		this.previous = Previous;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String Current) {
		this.current = Current;
	}
	public String getTokenPrevious() {
		return tokenPrevious;
	}
	public void setTokenPrevious(String tokenPrevious) {
		this.tokenPrevious = tokenPrevious;
	}
	public String getTokenCurrent() {
		return tokenCurrent;
	}
	public void setTokenCurrent(String tokenCurrent) {
		this.tokenCurrent = tokenCurrent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.sessionId != ((Info)obj).getSessionId()) return false;
		if (this.userId != ((Info)obj).getUserId()) return false;
		if (this.previous != ((Info)obj).getPrevious()) return false;
		if (this.current != ((Info)obj).getCurrent()) return false;
		if (this.tokenPrevious != ((Info)obj).getTokenPrevious()) return false;
		if (this.tokenCurrent != ((Info)obj).getTokenCurrent()) return false;
		return true;
	}
	public void reset() {
		this.userId = null;
		this.previous = null;
		this.current = null;
		this.tokenPrevious = null;
		this.tokenCurrent = null;
		
	}
	@Override
	public String toString() {
		return " sessionId "+sessionId
		+" userId " + userId
		+" previous " + previous
		+" current " + current
		+" tokenPrevious" + tokenPrevious
		+" tokenCurrent" + tokenCurrent;
	}
	


}
