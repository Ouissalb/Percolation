# Percolation
A program to estimate the value of the percolation threshold via Monte Carlo simulation.

This is part of an assignment for the "Algorithms, Part I" course, offered by Princeton University.

Percolation is modeled using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)


# Monte Carlo simulation:

To estimate the percolation threshold, the following has been implemented:

Initialize all sites to be blocked.
Repeat the following until the system percolates:
Choose a site uniformly at random among all blocked sites.
Open the site.
By repeating this computation experiment T times and averaging the results, we obtain a more accurate estimate of the percolation threshold. 

# Usage:

    java-algs4 PercolationStats 200 100
    
    mean = 0.5931697499999999
    stddev = 0.010672781329180548
    95% confidence interval = [0.5910778848594805, 0.5952616151405193]
