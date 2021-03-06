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

package org.apache.flex.compiler.internal.tree.as;

import org.apache.flex.compiler.projects.ICompilerProject;
import org.apache.flex.compiler.tree.ASTNodeID;

/**
 * A nil node is a placeholder used to distinguish a missing optional node.
 * Examples are "for (optE1; optE2; optE3)" or any positional construct of the
 * form "x,<nil>,z"
 */
public final class NilNode extends ExpressionNodeBase
{
    // TODO Why no constructor?
    
    //
    // NodeBase overrides
    //

    @Override
    public ASTNodeID getNodeID()
    {
        return ASTNodeID.NilID;
    }

    //
    // ExpressionNodeBase overrides
    //

    @Override
    protected NilNode copy()
    {
        return new NilNode();
    }

    @Override
    public boolean isDynamicExpression(ICompilerProject project)
    {
        return false;
    }
}
