/*
 * Copyright 2013 Basho Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basho.riak.client.core.netty;

import com.basho.riak.client.core.RiakResponseListener;
import com.basho.riak.client.core.util.Constants;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 * @author Brian Roach <roach at basho dot com>
 * @since 2.0
 */
public class RiakChannelInitializer extends ChannelInitializer<SocketChannel>
{

    private final RiakResponseListener listener;
    private final long timeout;

    public RiakChannelInitializer(RiakResponseListener listener, long timeout)
    {
        super();
        this.listener = listener;
        this.timeout = timeout;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception
    {
        ChannelPipeline p = ch.pipeline();
        p.addLast(Constants.MESSAGE_CODEC, new RiakMessageCodec());
        p.addLast(Constants.OPERATION_ENCODER, new RiakOperationEncoder());
        if (timeout > 0)
        {
            p.addLast(Constants.READTIMEOUT_HANDLER, new RiakReadTimeoutHandler(timeout));
        }
        p.addLast(Constants.RESPONSE_HANDLER, new RiakResponseHandler(listener));
    }

}
