# CSC439_LRUProxy

---------------------------------------------------------------------
Checking out the Repository
---------------------------------------------------------------------
To properly checkout the repository, simply go to the GitHub link (https://github.com/k3j123/CSC439_LRUProxy),
and click on the green button that says "Clone or Download" --> "Download Zip".


---------------------------------------------------------------------
Running the Ant Build
---------------------------------------------------------------------
To run the build.xml file, simply change into the directory where the project zip was downloaded,
and then type the command "ant report". This will effectively compile all the code and run the JUnit tests
via the TestCacheSuite class.

---------------------------------------------------------------------
LRU Proxy Description
---------------------------------------------------------------------
This Least Recently Used Proxy remove sites that have not been used for a while,
and if we revisit a site it will refresh the site variable. Here is a excerpt from the program document.

In this project, you need to implement a simple web proxy server with a caching
functionality. You also need to implement the LRU (Least Recently Used) cache
replacement algorithm for the proxy server. For more information on LRU, please refer
to http://en.wikipedia.org/wiki/Cache_algorithms#Least_Recently_Used. The proxy
server uses I/O to simulate the process of receiving HTTP requests and returning HTTP
responses. The proxy server reads web requests from an input file (a web request file) to
simulate the process of receiving HTTP requests from clients. The proxy server prints
out the responses on the screen to simulate the process of returning HTTP responses to
clients. The input file (input.txt) is posted with this assignment.

The processing flow of the proxy server is as follows:
1. It reads one request from the input file (input.txt) every second.

2. It checks if the requested web page is cached in the cache of the proxy server. If yes, it
prints out the cached web page on the screen and records an entry in a log file
(output.log). The log entry consists of a timestamp field, a web request URL field, and a
cache hit/miss/eviction field. A “cache hit” log entry looks like this: "Mon June 13
03:25:56 2011 www.google.com cache hit". Otherwise, it downloads the requested
web page from the corresponding web site. Upon receiving the web page, it needs to
store the web page in the cache of the proxy server first and then prints out the web page
on the screen, and finally writes an entry in the log file. A “cache miss” log entry looks
like this: "Mon June 13 03:25:57 2011 www.ask.com cache miss".

3. The cache of the proxy server can only cache a limited number of web pages. The
cache limit (the maximum number of web pages that the proxy server can cache in the
cache) is passed into the proxy server from the command line. If the number of cached
web pages is equal to the limit, the proxy server considers the cache is full. When the
cache is not full, the proxy server can save a web page into the cache easily. When the
cache is full, then the proxy server has to evict the least recently used web page from the
cache to make room for the new web page. Thus, the proxy server needs to save a
timestamp with each cached web page. When the cache replacement occurs, you need to
record an entry in the log file to indicate which cached page is evicted. A “cache
eviction” log entry looks like this: "Mon June 13 03:25:58 2011 www.ask.com the
cached page is evicted"