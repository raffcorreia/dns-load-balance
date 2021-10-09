$TTL    604800
@       IN      SOA     ns1.test-be.com. root.test-be.com. (
                  3       ; Serial
             604800     ; Refresh
              86400     ; Retry
            2419200     ; Expire
             604800 )   ; Negative Cache TTL
;
; name servers - NS records
     IN      NS      ns1.test-be.com.

; name servers - A records
test-be.com.        IN      A      172.2.0.3
test-be.com.        IN      A      172.2.0.4
test-be.com.        IN      A      172.2.0.5