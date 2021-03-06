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

/**
 * This AST node represents the instance of <code>mx.core.ClassFactory</code>
 * that the compiler implicitly creates as the value for a property of type
 * <code>mx.core.IFactory</code>.
 * <p>
 * An {@link IMXMLFactoryNode} has exactly one child, which is always an
 * {@link IMXMLClassNode} specifying the <code>generator</code> class for the
 * <code>ClassFactory</code> (i.e., the class from which the factory creates
 * instances). Gordon Smith
 */
public interface IMXMLFactoryNode extends IMXMLInstanceNode
{
    /**
     * Gets the {@link IMXMLClassNode} that is this node's sole child.
     */
    IMXMLClassNode getClassNode();
}
