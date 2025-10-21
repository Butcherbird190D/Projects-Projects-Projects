Description:
----------------

This is the eventual residing folder for experiments involving cluster computing using multiple servers.

The current conceptualized inspiration revolves around the Compute Blade Module for Raspberry Pi Compute Module 4/5. 
This concept is likely to be the end goal, as development of a cluster requires knowledge of the software before buying $500+ of enterprise grade hardware.

Current project being developed as of 10/20/2025:
	
		
	---Local Virtual Cluster--- [Cluster V.1]


Objective: Spin up multiple virtual machines (VirtualBox or VMware), network them together, and use a lightweight framework like OpenMPI or Docker Swarm for clustering.

This should give practical experience with distributed job execution and network orchestration concepts.

Proposed tasks for Cluster V.1:

- Run a Python script that splits computation-heavy tasks (e.g., hashing, image analysis, prime number finding) across nodes with MPI or Dask.
- Parallel file hashing or checksum comparison (Python + mpi4py)
- Distributed log analysis (e.g., forensic timeline) (Dask + Elasticsearch)


Managing this project alongside my other schoolwork is likely to prove as the most difficult part of this experiment.