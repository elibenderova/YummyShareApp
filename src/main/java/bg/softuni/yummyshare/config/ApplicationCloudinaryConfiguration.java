package bg.softuni.yummyshare.config;

import com.cloudinary.Cloudinary;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")
public class ApplicationCloudinaryConfiguration {
    private String cloudName;
    private String apiKey;
    private String apiSecret;

    public String getCloudName() {
        return cloudName;
    }

    /**
     * Sets the cloud name associated with the cloudinary account.
     * @param cloudName the cloud name associated with the cloudinary account.
     * @return this
     */
    public ApplicationCloudinaryConfiguration setCloudName(String cloudName) {
        this.cloudName = cloudName;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public ApplicationCloudinaryConfiguration setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public ApplicationCloudinaryConfiguration setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

}
