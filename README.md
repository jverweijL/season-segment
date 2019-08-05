Dynamically create pricingplans with the right season.


Make sure to configure the following property in portal-ext.properties
```
segment.ipgeolocation.apikey=
```

The module will lookup the ipnumber from the client but if you are running on localhost (dev)
you might want to hard-wire the ip. In this case you can set the following property in portal-ext.properties
```
segment.season.myip
```

