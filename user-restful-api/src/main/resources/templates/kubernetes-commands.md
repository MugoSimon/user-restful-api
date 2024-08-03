# Azure Kubernetes Service (AKS) Commands

This README provides a set of commands to manage and interact with an Azure Kubernetes Service (AKS) cluster using Azure CLI and `kubectl`.

## Azure CLI Commands

### Check if you are logged in to Azure
```bash
# Show the current logged-in Azure account
az account show
```

### Log in to your Azure account.
```bash
# login
az login
```

### Set the subscription (if you have multiple subscriptions)
```bash
# set account
az account set --subscription <YourSubscriptionName>
```

### Get AKS credentials (to configure kubectl):
```bash
# get-credentials
az aks get-credentials --resource-group <YourResourceGroup> --name <YourAKSName>
```

### Verify you can connect to your cluster
```bash
# Display cluster information to verify the connection
kubectl cluster-info

## example
# Display cluster information to verify the connection
kubectl cluster-info
```

### Get the list of nodes in your cluster
```bash
# List all nodes in the current Kubernetes cluster
kubectl get nodes
```

### Get the list of pods in the default namespace
```bash
# List all namespaces in the current Kubernetes cluster
kubectl get namespaces
```

### Describe a specific pod
```bash
# Show detailed information about the specified pod
kubectl describe pod <podName>

## example
# Show detailed information about the 'my-pod' pod
kubectl describe pod my-pod
```

### View the logs of a specific pod
```bash
# Fetch and display logs from the specified pod
kubectl logs <podName>

## example
# Fetch and display logs from the 'my-pod' pod
kubectl logs my-pod
```

### Check the deployment status
```bash
# List all deployments in the current namespace
kubectl get deployments

## example
# List all deployments in the current namespace
kubectl get deployments
```

### Describe a specific deployment
```bash
# Show detailed information about the specified deployment
kubectl describe deployment <DeploymentName>

## example
# Show detailed information about the "cooling-app-v2.1.9" deployment
kubectl describe deployment cooling-app-v2.1.9
```

### Check the services in your cluster
```bash
# List all services in the current namespace
kubectl get services

## example
# List all services in the current namespace
kubectl get services
```

### Fetch and follow logs from specific containers in labeled pods
```bash
# Fetch and follow the last 100 lines of logs from the 'specified-container-name' container
# in pods labeled with 'app=<specified-label>'
kubectl logs -l app=<specified-label> -c <specified-container-name> --tail 100 -f

## example
# Fetch and follow the last 100 lines of logs from the 'billing-plugins' container
# in pods labeled with 'app=billing-plugins'
kubectl logs -l app=billing-plugins -c billing-plugins --tail 100 -f
```

### Execute a bash shell in a specific pod
```bash
# Start an interactive bash shell in the 'specified-pod-name' pod
# in the 'specified-namespace' namespace
kubectl exec --stdin --tty <specified-pod-name> -n <specified-namespace> -- /bin/bash

## example
# Start an interactive bash shell in the 'billing-flow-deployment-7558ff859d-njjmt' pod
# in the 'billing' namespace
kubectl exec --stdin --tty billing-flow-deployment-7558ff859d-njjmt -n billing -- /bin/bash
````

### Telnet to a specific URL and port
```bash
# Initiate a telnet session to the specified hostname and port
telnet <hostname> <port>

## example
# Initiate a telnet session to the specified hostname (IP address) and port
telnet 10.0.66.214 8080
```

