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

package org.apache.flex.compiler.tree.mxml;

import org.apache.flex.compiler.tree.as.IExpressionNode;
import org.apache.flex.compiler.tree.as.ILiteralNode;

/**
 * This AST node represents an MXML <code>&lt;String&gt;</code> tag.
 * <p>
 * An {@link IMXMLStringNode} has exactly one child node: an
 * {@link IExpressionNode} representing an <code>String</code> value. It will be
 * either an {@link ILiteralNode}, an {@link IMXMLDataBindingNode}, or
 * an {@link IMXMLConcatenatedDataBindingNode}.
 */
public interface IMXMLStringNode extends IMXMLExpressionNode
{
    /**
     * Gets the value of the <code>&lt;String&gt;</code> tag as a String.
     * 
     * @return The value as a Java <code>String</code>.
     */
    String getValue();
}
