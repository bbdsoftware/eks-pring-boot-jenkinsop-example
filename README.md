
#  Containerized spring boot app 

---
## NOTE

This is an example project used and referenced in the bellow repos
Please see [Part 3 setting up CD ](https://github.com/bbdsoftware/eks-argo-cd) for details on how to deploy this application using argocd 

## DOCKER
You will need to add a kubernetes secrets for jenkins to pick up the docker credentials
eg 

```
apiVersion: v1
   kind: Secret
   metadata:
     name: dockerhub-creds
   annotations:
    "jenkins.io/credentials-description" : "credentials from Kubernetes"
   stringData:
     username: USER
     password: PASSWORD
   
```

**Reference**  
1.See the repo for examples https://github.com/bbdsoftware/eks-jenkins-ci/tree/master/jenkins  
2.https://jenkinsci.github.io/kubernetes-credentials-provider-plugin/

You will also need to supply the image repo in your pom file

```
....
            <configuration>
                    <to>
                        <image>docker.io/YOURDOCKERREPO/spring-boot-k8s-jenkinsop-example:${project.version}</image>
                    </to>
            </configuration>
....            
```                



### Starter Lib

A starter library is used here for bootstrapping certain common requirements namely  

* Swagger
* Feature toggling
* Opinionated Exceptions and handling
* Opinionated Api Payload 
* Logging utilities via Annotations

For more information please see  https://github.com/bbdsoftware/bbd-spring-boot-web-starter

```xml
<dependency>
    <groupId>com.bbdsoftware</groupId>
    <artifactId>bbd-spring-boot-web-starter</artifactId>
    <version>1.0-0-SNAPSHOT</version>
</dependency>
```


Background:

* [Part 1 setting up EKS ](https://github.com/bbdsoftware/eks-bootstrap)
* [Part 2 setting up CI](https://github.com/bbdsoftware/eks-jenkins-ci)
* [Part 3 setting up CD ](https://github.com/bbdsoftware/eks-argo-cd)


## Tools
- [Maven](https://maven.apache.org/)
- [Jenkins Operator](https://github.com/jenkinsci/kubernetes-operator)
- [Jib Maven plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)
- [Spring Boot](https://spring.io/projects/spring-boot)
