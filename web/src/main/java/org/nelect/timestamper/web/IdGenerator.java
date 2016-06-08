package org.nelect.timestamper.web;

import java.lang.ref.SoftReference;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

/**
 * Created by Michael on 2016/6/7.
 */
@Component
public class IdGenerator {

    private final int length = 16;

    private final Queue<SoftReference<SecureRandom>> randomRefs = new ConcurrentLinkedQueue<>();

    public String generateId() {

        byte random[] = new byte[length];

        StringBuilder builder = new StringBuilder();

        getRandomBytes(random);

        for (byte b : random) {
            byte b1 = (byte) ((b & 0xf0) >> 4);
            byte b2 = (byte) (b & 0x0f);
            builder.append((b1 < 10 ? (char) ('0' + b1) :
                (char) ('a' + (b1 - 10))));
            builder.append((b2 < 10 ? (char) ('0' + b2) :
                (char) ('a' + (b2 - 10))));
        }

        return builder.toString();
    }

    private void getRandomBytes(byte[] bytes) {

        SoftReference<SecureRandom> ref = randomRefs.poll();
        SecureRandom random;

        if (ref == null || (random = ref.get()) == null) {
            random = createSecureRandom();
            ref = new SoftReference<>(random);
        }

        random.nextBytes(bytes);
        randomRefs.add(ref);
    }

    private SecureRandom createSecureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException nsae) {
            return new SecureRandom();
        }
    }
}
