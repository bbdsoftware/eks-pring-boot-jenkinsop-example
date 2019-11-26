
#  Containerized spring boot app build using Jenkins Operator on kubernetes

## Tools

- maven
- Jenkins-operator  - [https://github.com/jenkinsci/kubernetes-operator]
- jib maven plugin -  [https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin]
- spring boot

### Build Set up
* Ensure the operator installation is completed. Flow the institutions at [https://github.com/jenkinsci/kubernetes-operator/blob/master/documentation/installation.md]

* Once competed apply the [jenkins-mater.yaml] (setup/jenkins-operator/jenkins-master.yaml)
  This will create a jenkins master pod with the seed jb configuration for this repository
    ```
    kubectl apply -f setup/jenkins-operator/jenkins-master.yaml
    ```
*  The build pipeline defined in *cicd/pipelines/JenkinsFile* use docker credentials that are supplyed vai kubernetes secret.
   The plugin installed int he jenkins master will load the docker hub credentials. Update the secret with the relevant credentials before applying the the cluster  
    ```
    kubectl apply -f setup/jenkins-operator/https://github.com/jenkinsci/kubernetes-credentials-plugin
    ```
  

  
  


