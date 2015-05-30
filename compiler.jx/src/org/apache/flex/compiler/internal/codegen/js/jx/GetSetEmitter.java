/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.flex.compiler.internal.codegen.js.jx;

import java.util.Set;

import org.apache.flex.compiler.codegen.ISubEmitter;
import org.apache.flex.compiler.codegen.js.IJSEmitter;
import org.apache.flex.compiler.definitions.IClassDefinition;
import org.apache.flex.compiler.internal.codegen.as.ASEmitterTokens;
import org.apache.flex.compiler.internal.codegen.js.JSDocEmitterTokens;
import org.apache.flex.compiler.internal.codegen.js.JSEmitterTokens;
import org.apache.flex.compiler.internal.codegen.js.JSSessionModel.PropertyNodes;
import org.apache.flex.compiler.internal.codegen.js.JSSubEmitter;
import org.apache.flex.compiler.internal.codegen.js.flexjs.JSFlexJSEmitter;
import org.apache.flex.compiler.internal.codegen.js.goog.JSGoogEmitterTokens;

public class GetSetEmitter extends JSSubEmitter implements
        ISubEmitter<IClassDefinition>
{

    public GetSetEmitter(IJSEmitter emitter)
    {
        super(emitter);
    }

    @Override
    public void emit(IClassDefinition definition)
    {
        // TODO (mschmalle) will remove this cast as more things get abstracted
        JSFlexJSEmitter fjs = (JSFlexJSEmitter) getEmitter();

        if (!getModel().getPropertyMap().isEmpty())
        {
            writeNewline();
            writeNewline();
            writeNewline();
            write(JSGoogEmitterTokens.OBJECT);
            write(ASEmitterTokens.MEMBER_ACCESS);
            write(JSEmitterTokens.DEFINE_PROPERTIES);
            write(ASEmitterTokens.PAREN_OPEN);
            String qname = definition.getQualifiedName();
            write(fjs.formatQualifiedName(qname));
            write(ASEmitterTokens.MEMBER_ACCESS);
            write(JSEmitterTokens.PROTOTYPE);
            write(ASEmitterTokens.COMMA);
            write(ASEmitterTokens.SPACE);
            write("/** @lends {" + fjs.formatQualifiedName(qname)
                    + ".prototype} */ ");
            writeNewline(ASEmitterTokens.BLOCK_OPEN);

            Set<String> propertyNames = getModel().getPropertyMap().keySet();
            boolean firstTime = true;
            for (String propName : propertyNames)
            {
                if (firstTime)
                    firstTime = false;
                else
                    writeNewline(ASEmitterTokens.COMMA);

                PropertyNodes p = getModel().getPropertyMap().get(propName);
                writeNewline("/** @expose */");
                write(propName);
                write(ASEmitterTokens.COLON);
                write(ASEmitterTokens.SPACE);
                writeNewline(ASEmitterTokens.BLOCK_OPEN);
                if (p.getter != null)
                {
                    write(ASEmitterTokens.GET);
                    write(ASEmitterTokens.COLON);
                    write(ASEmitterTokens.SPACE);
                    write(JSDocEmitterTokens.JSDOC_OPEN);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.ATSIGN);
                    write(ASEmitterTokens.THIS);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.BLOCK_OPEN);
                    write(fjs.formatQualifiedName(qname));
                    write(ASEmitterTokens.BLOCK_CLOSE);
                    write(ASEmitterTokens.SPACE);
                    write(JSDocEmitterTokens.JSDOC_CLOSE);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.FUNCTION);
                    fjs.emitParameters(p.getter.getParameterNodes());

                    fjs.emitDefinePropertyFunction(p.getter);
                }
                if (p.setter != null)
                {
                    if (p.getter != null)
                        writeNewline(ASEmitterTokens.COMMA);

                    write(ASEmitterTokens.SET);
                    write(ASEmitterTokens.COLON);
                    write(ASEmitterTokens.SPACE);
                    write(JSDocEmitterTokens.JSDOC_OPEN);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.ATSIGN);
                    write(ASEmitterTokens.THIS);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.BLOCK_OPEN);
                    write(fjs.formatQualifiedName(qname));
                    write(ASEmitterTokens.BLOCK_CLOSE);
                    write(ASEmitterTokens.SPACE);
                    write(JSDocEmitterTokens.JSDOC_CLOSE);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.FUNCTION);
                    fjs.emitParameters(p.setter.getParameterNodes());

                    fjs.emitDefinePropertyFunction(p.setter);
                }
                write(ASEmitterTokens.BLOCK_CLOSE);
            }
            writeNewline(ASEmitterTokens.BLOCK_CLOSE);
            write(ASEmitterTokens.PAREN_CLOSE);
            write(ASEmitterTokens.SEMICOLON);
        }
        if (!getModel().getStaticPropertyMap().isEmpty())
        {
            write(JSGoogEmitterTokens.OBJECT);
            write(ASEmitterTokens.MEMBER_ACCESS);
            write(JSEmitterTokens.DEFINE_PROPERTIES);
            write(ASEmitterTokens.PAREN_OPEN);
            String qname = definition.getQualifiedName();
            write(fjs.formatQualifiedName(qname));
            write(ASEmitterTokens.COMMA);
            write(ASEmitterTokens.SPACE);
            write("/** @lends {" + fjs.formatQualifiedName(qname) + "} */ ");
            writeNewline(ASEmitterTokens.BLOCK_OPEN);

            Set<String> propertyNames = getModel().getStaticPropertyMap()
                    .keySet();
            boolean firstTime = true;
            for (String propName : propertyNames)
            {
                if (firstTime)
                    firstTime = false;
                else
                    writeNewline(ASEmitterTokens.COMMA);

                PropertyNodes p = getModel().getStaticPropertyMap().get(
                        propName);
                writeNewline("/** @expose */");
                write(propName);
                write(ASEmitterTokens.COLON);
                write(ASEmitterTokens.SPACE);
                writeNewline(ASEmitterTokens.BLOCK_OPEN);
                if (p.getter != null)
                {
                    write(ASEmitterTokens.GET);
                    write(ASEmitterTokens.COLON);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.FUNCTION);
                    fjs.emitParameters(p.getter.getParameterNodes());

                    fjs.emitDefinePropertyFunction(p.getter);
                }
                if (p.setter != null)
                {
                    if (p.getter != null)
                        writeNewline(ASEmitterTokens.COMMA);

                    write(ASEmitterTokens.SET);
                    write(ASEmitterTokens.COLON);
                    write(ASEmitterTokens.SPACE);
                    write(ASEmitterTokens.FUNCTION);
                    fjs.emitParameters(p.setter.getParameterNodes());

                    fjs.emitDefinePropertyFunction(p.setter);
                }
                write(ASEmitterTokens.BLOCK_CLOSE);
            }
            writeNewline(ASEmitterTokens.BLOCK_CLOSE);
            write(ASEmitterTokens.PAREN_CLOSE);
            write(ASEmitterTokens.SEMICOLON);
        }
    }
}