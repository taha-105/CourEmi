package ma.ac.emi.coursemi.conf;

public interface SecurityParams {
	public static final String JWT_HEADER_NAME="Authorization";
	public static final String SECRET="EMICOURSEMI";
	public static final long EXPIRATION=1*60*60*24;
	public static final String HEADER_PREFIX="Bearer ";
}
