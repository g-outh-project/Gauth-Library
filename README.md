# Gauth-Library
## How To Use?

1. ### 라이브러리 다운로드

* AndroidManifast

  ```xml
  //추가
  <uses-permission android:name="android.permission.INTERNET" />
  
  //추가
  <application
          ...
          android:usesCleartextTraffic="true" 
          tools:replace="android:theme">
    ...
  </application>
  ```

#### Gradle

* build.gradle (Project)

  ```groovy
  allprojects {
      repositories {
          maven { url 'https://jitpack.io' }
  				...
      }
  }
  ```

* build.gradle (App)

  ```groovy
  android {
    ...
      
    //추가
    dataBinding {
      enabled = true
    }
  }
  
  dependencies {
    	//추가
      implementation 'com.squareup.retrofit2:retrofit:2.9.0'
      implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
      implementation 'com.github.g-outh-project:Gauth-Library:TAG(최신버전)'
    	...
  }
  ```

#### MAVEN

* pom.xml

  ```xml
  //추가
  <repositories>
  	<repository>
        <id>jitpack.io</id>
  	    <url>https://jitpack.io</url>
    </repository>
  </repositories>
  
  <dependency>
  	<groupId>com.github.g-outh-project</groupId>
    <artifactId>Gauth-Library</artifactId>
    <version>Tag</version>
  </dependency>
  ```

2. ### DOCS

#### LOGIN

* GauthApi - Login

  ```kotlin
  val gauthApi = GauthApi()
  
  gauthApi.signIn(this, object : SignInListener {
    override fun onFail() {
  		//TODO(로그인 실패 로직)
    }
    
    override fun onSuccess(token: Token) {
  		//TODO(로그인 성공 로직)
    }
  })
  ```

* Token - DataClass

  ```kotlin
  data class Token(
    val accessToken : String,
    val refreshToken : String
  )
  ```

* SignIn - DataClass

  ```kotlin
  data class Token(
    val id : String,
    val password : String
  )
  ```

#### SIGNUP

* GauthApi - SignUp

  ```kotlin
  gauthApi.signUp(this, object : SignUpListener {
  	override fun onFail() {
    	//TODO(회원가입 실패로직)
    }
  	
    override fun onSuccess() {
  		//TODO(회원가입 성공 로직)
   	}
  
  })
  ```

* SignUp

  ```kotlin
  data class SignUp(
      val id : String,
      val password : String,
      val email : String,
      val school : String,
      val birth : String,
      val nickname: String,
      val name : String
  )
  ```

3. ### Example Code

https://github.com/g-outh-project/Gauth-Android-Example

4. ### 문의하기

email : seogunhee4@gmail.com
