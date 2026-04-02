Written by [@sun3k](https://github.com/Sunderw3k)

# Entry

![[Oringo Announcment.png|528]]

Well, that switch happened. I am too late to the party, as I do not really play skyblock.

Oringo is the OG client, I have a bigger soft spot for RGA, but it is what it is, it's got a big place in my heart nonetheless.

The backend is down, the discord bot is down, so I want to share the semi-cracked version from Late 2024/Early 2025. I don't think there were any updates since then anyways lol.

This vault will also be a write-up, which I did not write back then. Something I started doing recently is PROPERLY writing the protocols down, so this is proper homage to the client.

## My Backup
I have a loader from November 25 2024, and a client jar from November 27 2024.

TODO: Latest is from January 13 2025, diff them.

I have a BUNCH of net jars from Nov 27th, but they're all the same thing. To get the actual jar you can use the decryptor from `scripts/decrypt.py` on `0_465`; the left side is the solved code.

## Auth
As we probably all know by now, oringo was netloaded. 
There is a Rust JNI native written by Ciel (as far as I remember).

There unfortunately is NOT an `aarch64` build of it, so I cannot run it on my modern laptop. 
TODO: Reverse-engineer the JNI native?

It seems to be just 3 endpoints, [[Code Endpoint|/code/]], [[Auth2 Endpoint|/auth2/]], and [[Assets Endpoint|/assets/]].
Those are explained in the links, alongside all the info you need to know about them.

# Building
```sh
git clone https://github.com/Sunderw3k/OringoArchived
cd OringoArchived

# On linux
sh scripts/cert-linux.sh
sudo trust anchor certs/root-ca.pem

# On Windows
TODO

./gradlew build

# It binds on 80 and 443. 
# If that's a problem, you probably know how to change it.
sudo java -jar build/libs/Backend-1.0.0-all.jar
```

One important note is that you NEED a DNS. 
On windows it can be [acrylic](https://sourceforge.net/projects/acrylic/), some router software, or whatever. 
Same on Linux, `dnsmasq`, anything. You can figure it out, I believe.

In that DNS, you want to redirect `shiyu.moe` to `localhost`, or `127.0.0.1`, or your local ip.