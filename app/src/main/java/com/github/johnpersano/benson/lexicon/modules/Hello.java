/**
 * Copyright 2014 John Persano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package com.github.johnpersano.benson.lexicon.modules;

import android.content.Context;

import com.github.johnpersano.benson.R;
import com.github.johnpersano.benson.lexicon.Query;
import com.github.johnpersano.benson.lexicon.Response;

import java.util.Arrays;
import java.util.List;

import me.palazzetti.adktoolkit.AdkManager;

public class Hello extends Query {

    @Override
    public List<String> getInputs() {

        return Arrays.asList("hello", "hey", "hi", "howdy");

    }

    @Override
    public Response getResponse(Context context, String hypothesis, AdkManager adkManager) {

        return new Response()
                .setReply(Response.getRandomReply(context.getResources().getStringArray(R.array.hello_default)))
                .setNestedLexicon(Arrays.asList(new PositiveStatus(), new NegativeStatus()));

    }

    private class PositiveStatus extends Query {

        @Override
        public List<String> getInputs() {

            return Arrays.asList("good", "great", "excellent", "wonderful", "dandy", "super", "terrific");

        }

        @Override
        public Response getResponse(Context context, String hypothesis, AdkManager adkManager) {

            return new Response()
                    .setReply(Response.getRandomReply(context.getResources().getStringArray(R.array.hello_positive_status)));

        }

    }

    private class NegativeStatus extends Query {

        @Override
        public List<String> getInputs() {

            return Arrays.asList("not good", "bad", "terrible", "horrible");

        }

        @Override
        public Response getResponse(Context context, String hypothesis, AdkManager adkManager) {

            return new Response()
                    .setReply(Response.getRandomReply(context.getResources().getStringArray(R.array.hello_negative_status)))
                    .setNestedLexicon(Arrays.asList(new YesJoke(), new NoJoke()));

        }

    }

    private class YesJoke extends Joke {

        @Override
        public List<String> getInputs() {

            return Arrays.asList("yes", "maybe", "okay", "sure", "certainly");

        }

        @Override
        public Response getResponse(Context context, String hypothesis, AdkManager adkManager) {

            return super.getResponse(context, hypothesis, adkManager);

        }

    }

    private class NoJoke extends Query {

        @Override
        public List<String> getInputs() {

            return Arrays.asList("no", "not now", "not at all", "absolutely not");

        }

        @Override
        public Response getResponse(Context context, String hypothesis, AdkManager adkManager) {

            return new Response()
                    .setReply(Response.getRandomReply(context.getResources().getStringArray(R.array.hello_no_joke)));

        }

    }

}