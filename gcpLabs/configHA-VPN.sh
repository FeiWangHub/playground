#Objectives
#
#In this lab, you learn how to perform the following tasks:
#Create two VPC networks and instances.
#Configure HA VPN gateways.
#Configure dynamic routing with VPN tunnels.
#Configure global dynamic routing mode.
#Verify and test HA VPN gateway configuration.

# Step1. Set up a Global VPC environment
# 1 In Cloud Shell, create a VPC network called vpc-demo:
gcloud compute networks create vpc-demo --subnet-mode custom
# 2 In Cloud Shell, create subnet vpc-demo-subnet1 in the region us-central1
gcloud compute networks subnets create vpc-demo-subnet1 \
--network vpc-demo --range 10.1.1.0/24 --region us-central1
# 3 Create subnet vpc-demo-subnet2 in the region us-east1:
Create subnet vpc-demo-subnet2 in the region us-east1:
# 4 Create a firewall rule to allow all custom traffic within the network:
gcloud compute firewall-rules create vpc-demo-allow-custom \
  --network vpc-demo \
  --allow tcp:0-65535,udp:0-65535,icmp \
  --source-ranges 10.0.0.0/8
# 5 Create a firewall rule to allow SSH, ICMP traffic from anywhere:
gcloud compute firewall-rules create vpc-demo-allow-ssh-icmp \
    --network vpc-demo \
    --allow tcp:22,icmp
# 6 Create a VM instance vpc-demo-instance1 in zone us-central1-b:
gcloud compute instances create vpc-demo-instance1 --zone us-central1-b --subnet vpc-demo-subnet1
# 7 Create a VM instance vpc-demo-instance2 in zone us-east1-b:
gcloud compute instances create vpc-demo-instance2 --zone us-east1-b --subnet vpc-demo-subnet2


#Task 2. Set up a simulated on-premises environment
# 1 in Cloud Shell, create a VPC network called on-prem:
gcloud compute networks create on-prem --subnet-mode custom
# 2 Create a subnet called on-prem-subnet1:
gcloud compute networks subnets create on-prem-subnet1 \
--network on-prem --range 192.168.1.0/24 --region us-central1
# 3 Create a firewall rule to allow all custom traffic within the network:
gcloud compute firewall-rules create on-prem-allow-custom \
  --network on-prem \
  --allow tcp:0-65535,udp:0-65535,icmp \
  --source-ranges 192.168.0.0/16
# 4 Create a firewall rule to allow SSH, RDP, HTTP, and ICMP traffic to the instances:
gcloud compute firewall-rules create on-prem-allow-ssh-icmp \
    --network on-prem \
    --allow tcp:22,icmp
# 5 Create an instance called on-prem-instance1 in the region us-central1:
gcloud compute instances create on-prem-instance1 --zone us-central1-a --subnet on-prem-subnet1


# Task 3. Set up an HA VPN gateway
# 1 In Cloud Shell, create an HA VPN in the vpc-demo network:
gcloud compute vpn-gateways create vpc-demo-vpn-gw1 --network vpc-demo --region us-central1
# 2 Create an HA VPN in the on-prem network:
gcloud compute vpn-gateways create on-prem-vpn-gw1 --network on-prem --region us-central1
# 3 View details of the vpc-demo-vpn-gw1 gateway to verify its settings:
gcloud compute vpn-gateways describe vpc-demo-vpn-gw1 --region us-central1
# 4 View details of the on-prem-vpn-gw1 vpn-gateway to verify its settings:
gcloud compute vpn-gateways describe on-prem-vpn-gw1 --region us-central1
#Create cloud routers
#1 Create a cloud router in the vpc-demo network:
gcloud compute routers create vpc-demo-router1 \
    --region us-central1 \
    --network vpc-demo \
    --asn 65001
#2 Create a cloud router in the on-prem network:
gcloud compute routers create on-prem-router1 \
    --region us-central1 \
    --network on-prem \
    --asn 65002


# Task 4. Create two VPN tunnels
# 1 Create the first VPN tunnel in the vpc-demo network:
gcloud compute vpn-tunnels create vpc-demo-tunnel0 \
    --peer-gcp-gateway on-prem-vpn-gw1 \
    --region us-central1 \
    --ike-version 2 \
    --shared-secret [SHARED_SECRET] \
    --router vpc-demo-router1 \
    --vpn-gateway vpc-demo-vpn-gw1 \
    --interface 0
# 2 Create the second VPN tunnel in the vpc-demo network:
gcloud compute vpn-tunnels create vpc-demo-tunnel1 \
    --peer-gcp-gateway on-prem-vpn-gw1 \
    --region us-central1 \
    --ike-version 2 \
    --shared-secret [SHARED_SECRET] \
    --router vpc-demo-router1 \
    --vpn-gateway vpc-demo-vpn-gw1 \
    --interface 1
# 3 Create the first VPN tunnel in the on-prem network:
gcloud compute vpn-tunnels create on-prem-tunnel0 \
    --peer-gcp-gateway vpc-demo-vpn-gw1 \
    --region us-central1 \
    --ike-version 2 \
    --shared-secret [SHARED_SECRET] \
    --router on-prem-router1 \
    --vpn-gateway on-prem-vpn-gw1 \
    --interface 0
# 4 Create the second VPN tunnel in the on-prem network:
gcloud compute vpn-tunnels create on-prem-tunnel1 \
    --peer-gcp-gateway vpc-demo-vpn-gw1 \
    --region us-central1 \
    --ike-version 2 \
    --shared-secret [SHARED_SECRET] \
    --router on-prem-router1 \
    --vpn-gateway on-prem-vpn-gw1 \
    --interface 1


#Task 5. Create Border Gateway Protocol (BGP) peering for each tunnel
# 1 Create the router interface for tunnel0 in network vpc-demo:
gcloud compute routers add-interface vpc-demo-router1 \
    --interface-name if-tunnel0-to-on-prem \
    --ip-address 169.254.0.1 \
    --mask-length 30 \
    --vpn-tunnel vpc-demo-tunnel0 \
    --region us-central1
# 2 Create the BGP peer for tunnel0 in network vpc-demo:
gcloud compute routers add-bgp-peer vpc-demo-router1 \
    --peer-name bgp-on-prem-tunnel0 \
    --interface if-tunnel0-to-on-prem \
    --peer-ip-address 169.254.0.2 \
    --peer-asn 65002 \
    --region us-central1
# 3 Create a router interface for tunnel1 in network vpc-demo:
gcloud compute routers add-interface vpc-demo-router1 \
    --interface-name if-tunnel1-to-on-prem \
    --ip-address 169.254.1.1 \
    --mask-length 30 \
    --vpn-tunnel vpc-demo-tunnel1 \
    --region us-central1
# 4 Create the BGP peer for tunnel1 in network vpc-demo:
gcloud compute routers add-bgp-peer vpc-demo-router1 \
    --peer-name bgp-on-prem-tunnel1 \
    --interface if-tunnel1-to-on-prem \
    --peer-ip-address 169.254.1.2 \
    --peer-asn 65002 \
    --region us-central1
# 5 Create a router interface for tunnel0 in network on-prem:
gcloud compute routers add-interface on-prem-router1 \
    --interface-name if-tunnel0-to-vpc-demo \
    --ip-address 169.254.0.2 \
    --mask-length 30 \
    --vpn-tunnel on-prem-tunnel0 \
    --region us-central1
# 6 Create the BGP peer for tunnel0 in network on-prem:
gcloud compute routers add-bgp-peer on-prem-router1 \
    --peer-name bgp-vpc-demo-tunnel0 \
    --interface if-tunnel0-to-vpc-demo \
    --peer-ip-address 169.254.0.1 \
    --peer-asn 65001 \
    --region us-central1
# 7 Create a router interface for tunnel1 in network on-prem:
gcloud compute routers add-interface  on-prem-router1 \
    --interface-name if-tunnel1-to-vpc-demo \
    --ip-address 169.254.1.2 \
    --mask-length 30 \
    --vpn-tunnel on-prem-tunnel1 \
    --region us-central1
# 8 Create the BGP peer for tunnel1 in network on-prem:
gcloud compute routers add-bgp-peer  on-prem-router1 \
    --peer-name bgp-vpc-demo-tunnel1 \
    --interface if-tunnel1-to-vpc-demo \
    --peer-ip-address 169.254.1.1 \
    --peer-asn 65001 \
    --region us-central1


#Task 6. Verify router configurations
# 1 View details of Cloud Router vpc-demo-router1 to verify its settings:
gcloud compute routers describe vpc-demo-router1 \
    --region us-central1
# 2 View details of Cloud Router on-prem-router1 to verify its settings:
gcloud compute routers describe on-prem-router1 \
    --region us-central1
# Configure firewall rules to allow traffic from the remote VPC
# 1 Allow traffic from network VPC on-prem to vpc-demo:
gcloud compute firewall-rules create vpc-demo-allow-subnets-from-on-prem \
    --network vpc-demo \
    --allow tcp,udp,icmp \
    --source-ranges 192.168.1.0/24
# 2 Allow traffic from vpc-demo to network VPC on-prem:
gcloud compute firewall-rules create on-prem-allow-subnets-from-vpc-demo \
    --network on-prem \
    --allow tcp,udp,icmp \
    --source-ranges 10.1.1.0/24,10.2.1.0/24
#Verify the status of the tunnels
#1 List the VPN tunnels you just created:
gcloud compute vpn-tunnels list
#2 Verify that vpc-demo-tunnel0 tunnel is up:
gcloud compute vpn-tunnels describe vpc-demo-tunnel0 \
      --region us-central1
#3 Verify that vpc-demo-tunnel1 tunnel is up:
gcloud compute vpn-tunnels describe vpc-demo-tunnel1 \
      --region us-central1
#4 Verify that on-prem-tunnel0 tunnel is up:
gcloud compute vpn-tunnels describe on-prem-tunnel0 \
      --region us-central1
#5 Verify that on-prem-tunnel1 tunnel is up:
gcloud compute vpn-tunnels describe on-prem-tunnel1 \
      --region us-central1

# Global routing with VPN
#1 Open a new Cloud Shell tab and update the bgp-routing mode from vpc-demo to GLOBAL:
gcloud compute networks update vpc-demo --bgp-routing-mode GLOBAL
#2 Verify the change:
gcloud compute networks describe vpc-demo
#3 From the Cloud Shell tab that is currently connected to the instance in network on-prem via ssh, ping the instance vpc-demo-instance2 in region us-east1:
ping -c 2 10.2.1.2

#Task 8. (Optional) Clean up lab environment
#Delete VPN tunnels
gcloud compute vpn-tunnels delete on-prem-tunnel0  --region us-central1
#Remove BGP peering
gcloud compute routers remove-bgp-peer vpc-demo-router1 --peer-name bgp-on-prem-tunnel0 --region us-central1
#Delete cloud routers
gcloud compute  routers delete on-prem-router1 --region us-central1
#Delete VPN gateways
gcloud compute vpn-gateways delete vpc-demo-vpn-gw1 --region us-central1
#Delete instances
gcloud compute instances delete vpc-demo-instance1 --zone us-central1-b
#Delete firewall rules
gcloud compute firewall-rules delete vpc-demo-allow-custom
#Delete subnets
gcloud compute networks subnets delete vpc-demo-subnet1 --region us-central1
#Delete VPC
gcloud compute networks delete vpc-demo
