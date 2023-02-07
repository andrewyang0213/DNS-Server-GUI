package org.openjfx;

import org.dns.DNSServer;
import org.dns.DNSZone;

public class StartDNS {
  public static void processInput() {

    // make the zone, which will exit() if the file is invalid in any way
    DNSZone zone = new DNSZone("testZone.zone");

    System.out.println("Starting DNS server: ");

    // make the server object then start listening for DNS requests
    DNSServer server = new DNSServer(zone);
    server.run();
  }

}