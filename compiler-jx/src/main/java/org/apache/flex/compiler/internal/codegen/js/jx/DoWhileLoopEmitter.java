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

import org.apache.flex.compiler.codegen.ISubEmitter;
import org.apache.flex.compiler.codegen.js.IJSEmitter;
import org.apache.flex.compiler.internal.codegen.as.ASEmitterTokens;
import org.apache.flex.compiler.internal.codegen.js.JSSubEmitter;
import org.apache.flex.compiler.internal.codegen.js.utils.EmitterUtils;
import org.apache.flex.compiler.tree.as.IASNode;
import org.apache.flex.compiler.tree.as.IContainerNode;
import org.apache.flex.compiler.tree.as.IWhileLoopNode;

public class DoWhileLoopEmitter extends JSSubEmitter implements
        ISubEmitter<IWhileLoopNode>
{
    public DoWhileLoopEmitter(IJSEmitter emitter)
    {
        super(emitter);
    }

    @Override
    public void emit(IWhileLoopNode node)
    {
        IContainerNode cnode = (IContainerNode) node.getChild(0);

        startMapping(node);
        write(ASEmitterTokens.DO);
        if (!EmitterUtils.isImplicit(cnode))
            write(ASEmitterTokens.SPACE);
        endMapping(node);

        IASNode statementContents = node.getStatementContentsNode();
        getWalker().walk(statementContents);

        IASNode conditionalExpressionNode = node.getConditionalExpressionNode();
        startMapping(node, statementContents);
        if (!EmitterUtils.isImplicit(cnode))
            write(ASEmitterTokens.SPACE);
        else
            writeNewline(); // TODO (mschmalle) there is something wrong here, block should NL
        write(ASEmitterTokens.WHILE);
        write(ASEmitterTokens.SPACE);
        write(ASEmitterTokens.PAREN_OPEN);
        endMapping(node);

        getWalker().walk(conditionalExpressionNode);

        startMapping(node, conditionalExpressionNode);
        write(ASEmitterTokens.PAREN_CLOSE);
        write(ASEmitterTokens.SEMICOLON);
        endMapping(node);
    }
}
