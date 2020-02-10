# MyMart
# stw300cem-final-project-Sprajapati123
stw300cem-final-project-Sprajapati123 created by GitHub Classroom



![minio_ANDROID1](https://github.com/stw300cem/stw300cem-final-project-Sprajapati123/blob/master/app/src/main/res/drawable/mymartlogin.png)

## Introduction

MyMart is a E-commerce application that refers to the buying of products online that reduces human effort and time. Today’s world is dominated by Information and technology, people spent most of the time in Internet. People prefer to buy  online instead of going to each individual shop. So MyMart is android application that simplifies shopping and reduces human effort and time. Among different type of E-commerce, MyMart is (Business to customer) B2C.

![minio_ANDROID1](https://github.com/stw300cem/stw300cem-final-project-Sprajapati123/blob/master/app/src/main/res/drawable/gitscreen.png)
## Dependencies

~~~
   implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'de.hdodenhof:circleimageview:3.0.0'

    implementation 'com.github.smarteist:autoimageslider:1.3.2'
    implementation 'com.github.bumptech.glide:glide:4.9.0'

    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0'
    androidTestImplementation 'androidx.test:runner:1.1.0'
    androidTestImplementation 'androidx.test:rules:1.1.0'
    androidTestImplementation 'androidx.test.ext:junit:1.1.0'




    implementation 'com.google.android.gms:play-services-maps:16.1.0'
    implementation 'com.google.android.gms:play-services-location:16.0.0'
    implementation 'com.google.android.gms:play-services-wearable:16.0.1'
~~~

## Aims
```a
    • To sell best products as per customer needs and demands.
    • To saves user time.
    • To make application more user friendly.
   ```
   
   
 ## Objectives
 ```b
    • Using of design pattern to make code more efficient.
    • Research on effective GUI
    • Testing the application, it should be bug free.
```

## features

 ```c
 
    • User Registration, login
    • User Login if they tilt the device to left.
    • User Login if they tilt the device to left.\\\\\\\\\
    • Viewing all items
    • Adding items to cart
    • proceed items to delivery.
    • Sending feedback
    • Google maps
    • Token for authorization.
    • Logout
 
 ```
 
 ## youtube link
 
```r

https://youtu.be/k7SjHjyP_6A  

```

## backend link of Api

```qw
https://github.com/stw304cem/t2-backend-api-Sprajapati123.git
```



# Rest Client
 ```
Retrofit
```

Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP requests.Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use GSon, but you can add custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP requests.

# CALL

An invocation of a Retrofit method that sends a request to a webserver and returns a response. Each call yields its own HTTP request and response pair. Use clone() to make multiple calls with the same parameters to the same webserver; this may be used to implement polling or to retry a failed call.
Calls may be executed synchronously with execute(), or asynchronously with enqueue(retrofit2.Callback<T>). In either case the call can be canceled at any time with cancel(). A call that is busy writing its request or reading its response may receive a IOException; this is working as designed.


## POST
For a POST request, to add form parameters/values, the getParams() method needs to be overridden and a Map needs to be returned.


## GET
Making GET Requests is simple. The example below uses JsonObjectRequest. It prepares a JsonObjectRequest and
passes and then adds it to RequestQueue. The JsonObject accepts 4 parameters (Http method, Url, Json values, Response Listener – Invoked on success, Error Listener – Invoked on failure).


## PUT
For PUT request, we need to Pass id. It is used to update the data

## DELETE
For DELETE request, we should pass id, it is used to delete the data.


## Conclusion
With the use of this application MyMart, user can do shopping with one click which simplifies the shopping experience. The product is not delivered until it is finalized by the user from My Cart option. The user can select the payment method before ordering any item. This simplifies the shopping experience for the user. 
       
