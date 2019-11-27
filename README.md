
#  Containerized spring boot app 

---
## NOTE

This is an example project used and referenced in the bellow repos
Please see [Part 3 setting up CD ](https://github.com/bbdsoftware/eks-argo-cd) for details on how to deploy this application using argocd 

### Starter Lib

A starter library is used here for bootstrapping certain common requirements namely  

* Swagger
* Feature toggling
* Opinionated Exceptions and handling
* Opinionated Api Payload 
* Logging utilises via Annotations

For more information please see  https://github.com/bbdsoftware/bbd-spring-boot-web-starter

```
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
- maven
- Jenkins-operator  - [https://github.com/jenkinsci/kubernetes-operator]
- jib maven plugin -  [https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin]
- spring boot
  

  
  


