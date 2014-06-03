
package com.packtpub.pf.blueprint.chat;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;
/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai
 * Date: 1/8/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
/**
 * A Simple {@link org.primefaces.push.Encoder} that decode a {@link Message} into a simple JSON object.
 */
public final class MessageEncoder implements Encoder<Message, String> {

    @Override
    public String encode(Message message) {
        return new JSONObject(message).toString();
    }

}